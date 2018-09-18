package cn.bgenius.analysis.spc.controller;

import cn.bgenius.analysis.spc.entity.TTest;
import cn.bgenius.analysis.spc.service.TestService;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: bgenius-pconnect
 * @description: 这时一个基本连通测试
 * @author: 金飞
 * @create: 2018-09-17 16:00
 **/
@RestController
@RequestMapping("v1/test")
public class TestController extends BaseController<TestService, TTest,Integer> {

    @ApiOperation("这是一个基本连通测试")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() throws Exception{
        return "analysis-spc-test";
    }

    @ApiOperation("这是一个基本连通测试/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test1() throws Exception{
        return "analysis-spc-test/";
    }

    @ApiOperation(value = "注意不要一次获取全部列表" )
    @RequestMapping(value = "getList/{key}", method = RequestMethod.GET)
    public JSONObject getTestList() throws Exception {
        List<TTest> todoes = baseServiceImpl.selectListAll();
        return JsonUtil.getSuccessJsonObject(todoes);
    }
}
