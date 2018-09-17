package cn.bgenius.pconnect.knw.controller;

import cn.bgenius.pconnect.knw.entity.TArticleCollect;
import cn.bgenius.pconnect.knw.service.ArticleService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/article")
public class ArticleController extends BaseController<ArticleService, TArticleCollect,Integer> {

    @ApiOperation(value = "注意不要一次获取全部列表" )
    @RequestMapping(value = "getList/{key}", method = RequestMethod.GET)
    public JSONObject getTodoList() throws Exception {
        List<TArticleCollect> todoes = baseServiceImpl.selectListAll();
        return JsonUtil.getSuccessJsonObject(todoes);
    }
}
