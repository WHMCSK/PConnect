package cn.bgenius.pconnect.todo.controller;

import cn.bgenius.pconnect.todo.entity.TTodoes;
import cn.bgenius.pconnect.todo.service.TtodoesService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/tTodo")
public class TodoController extends BaseController<TtodoesService, TTodoes,Integer> {

    @ApiOperation(value = "注意不要一次获取全部列表" )
    @RequestMapping(value = "getList/{key}", method = RequestMethod.GET)
    public JSONObject getTodoList() throws Exception {
        List<TTodoes> todoes = baseServiceImpl.selectListAll();
        return JsonUtil.getSuccessJsonObject(todoes);
    }
}
