package cn.bgenius.pconnect.user.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.api.vo.authority.SessionInfo;
import cn.bgenius.pconnect.common.util.HttpUtil;
import cn.bgenius.pconnect.user.entity.TUserLog;
import cn.bgenius.pconnect.user.service.TUserLogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 金飞 on 2017/7/10.
 * 记录调用Controller的日志
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ThreadLocal<Long> timeTreadLocal = new ThreadLocal<>();

    @Autowired
    private TUserLogService tUserLogService;

    @Pointcut("execution(* cn.bgenius.pconnect.user.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    //@Pointcut("execution(* cn.bgenius.pconnect.user.controller..*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        timeTreadLocal.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求的request
        HttpServletRequest request = attributes.getRequest();
        //获取所有请求的参数，封装为map对象
        // Map<String,Object> parameterMap = getParameterMap(request);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取被拦截的方法
        Method method = methodSignature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        logger.info("AOP begin ,请求开始方法  :{}", method.getDeclaringClass() + "." + methodName + "()");
        //获取所有请求参数key和value
        String keyValue = getReqParameter(request);
        logger.info("请求url = {}", request.getRequestURL().toString());
        logger.info("请求方法requestMethod = {}", request.getMethod());
        logger.info("请求资源uri = {}", request.getRequestURI());
        logger.info("所有的请求参数 key：value = {}", keyValue);
        TUserLog tUserLog = new TUserLog();
        tUserLog.setAction(method.getDeclaringClass()+"");
        tUserLog.setOptTime(new Date());
        tUserLog.setMethod(methodName);
        tUserLog.setSessionId(request.getSession().getId());
        try {
            String ip = HttpUtil.getIpAddr(request);
            logger.info("PConnect: ip:{}", ip);
            tUserLog.setIp(ip);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("PConnect: ip error");
        }
        SessionInfo sessionInfo  = (SessionInfo) request.getSession().getAttribute("sessionInfo");
        if(sessionInfo!=null && StringUtils.isNotBlank(sessionInfo.getUsername())){
            tUserLog.setUsername(sessionInfo.getUsername());
            tUserLog.setCreateTime(new Date());
            tUserLogService.insert(tUserLog);
        }
    }


    @After("log()")
    public void after() {
        logger.info("aop的after()方法");
    }

    //controller请求结束返回时调用
    @AfterReturning(returning = "result", pointcut = "log()")
    public void afterReturn(Object result) {
        logger.info("AOP afterReturn,返回值result = {}", result.toString());
        // System.out.println("返回值="+result.toString());
        long startTime = timeTreadLocal.get();
        double callTime = (System.currentTimeMillis() - startTime) / 1000.0;
        logger.info("调用controller花费时间time = {}s", callTime);
    }

    /**
     * 获取所有请求参数，封装为map对象
     *
     * @return
     */
    public Map<String, Object> getParameterMap(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        StringBuilder stringBuilder = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            String keyValue = key + " : " + value + " ; ";
            stringBuilder.append(keyValue);
            parameterMap.put(key, value);
        }
        return parameterMap;
    }

    public String getReqParameter(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Enumeration<String> enumeration = request.getParameterNames();
        JSONArray jsonArray = new JSONArray();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            JSONObject json = new JSONObject();
            json.put(key, value);
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
}
