package cn.bgenius.pconnect.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ReqtoBeanUtil {
	private static final Logger LOG = LogManager.getLogger(ReqtoBeanUtil.class);
	public ReqtoBeanUtil() {
	}

	 /**
	  * 自动匹配参数赋值到实体bean中
     */
    public static void toBean(Object bean, HttpServletRequest req){
        Class<?> clazz = bean.getClass();
        Method ms[] = clazz.getDeclaredMethods();
        String mname;
        String field;
        String fieldType;
        String value;
        for(Method m : ms){
            mname = m.getName();
            if(!mname.startsWith("set")
                    || ArrayUtils.isEmpty(m.getParameterTypes())){
                continue;
            }
            try{
                field = mname.toLowerCase().charAt(3) + mname.substring(4, mname.length());
                value = req.getParameter(field);
                if(LOG.isDebugEnabled()){
                	LOG.debug(field + " = " + value);
                }
                if(StringUtils.isEmpty(value)){
                    continue;
                }
                fieldType = m.getParameterTypes()[0].getName();
                //以下可以确认value为String类型
                if(String.class.getName().equals(fieldType)){
                    m.invoke(bean, (String)value);
                }else if((Byte.class.getName().equals(fieldType)||byte.class.getName().equals(fieldType)) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Byte.valueOf((String)value));
                }else if((Integer.class.getName().equals(fieldType)||int.class.getName().equals(fieldType)) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Integer.valueOf((String)value));
                }else if((Short.class.getName().equals(fieldType)||short.class.getName().equals(fieldType)) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Short.valueOf((String)value));
                }else if((Long.class.getName().equals(fieldType)||long.class.getName().equals(fieldType)) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Long.valueOf((String)value));
                }else if((Float.class.getName().equals(fieldType)||float.class.getName().equals(fieldType)) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Float.valueOf((String)value));
                }else if((Double.class.getName().equals(fieldType)||double.class.getName().equals(fieldType)) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Double.valueOf((String)value));
                }else if(BigDecimal.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, new BigDecimal(value));
                }else if(Date.class.getName().equals(fieldType)){
                	String attach0 = "";
                	value = value.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
                	for(int i =0; i < (17 - value.length()); i++) {
                		attach0 += "0";
                	}
                    m.invoke(bean, DateUtil.toDate(value + attach0));
                }else{
                    m.invoke(bean, value);
                }
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
