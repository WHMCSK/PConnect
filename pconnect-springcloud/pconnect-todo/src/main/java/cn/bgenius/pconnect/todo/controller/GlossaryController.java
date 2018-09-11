package cn.bgenius.pconnect.todo.controller;

import cn.bgenius.pconnect.todo.entity.TGlossary;
import cn.bgenius.pconnect.todo.service.GlossaryService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/glossary")
public class GlossaryController extends BaseController<GlossaryService, TGlossary,Integer> {

    @ApiOperation(value = "注意不要一次获取全部列表" )
    @RequestMapping(value = "getList/{key}", method = RequestMethod.GET)
    public JSONObject getTodoList() throws Exception {
        List<TGlossary> todoes = baseServiceImpl.selectListAll();
        return JsonUtil.getSuccessJsonObject(todoes);
    }
}
