package cn.bgenius.analysis.spc.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/test")
public class TestController {

    @ApiOperation("这时一个基本连通测试")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() throws Exception{
        return "analysis-spc-test";
    }

    @ApiOperation("这时一个基本连通测试/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test1() throws Exception{
        return "analysis-spc-test/";
    }
}
