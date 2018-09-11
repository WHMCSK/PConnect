package cn.bgenius.pconnect.user.controller;

import cn.bgenius.pconnect.user.entity.TDict;
import cn.bgenius.pconnect.user.service.TDictService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fier on 2017/11/28.
 */
@RestController
@RequestMapping("v1/tDict")
public class TDictController extends BaseController<TDictService, TDict,Integer> {

    @GetMapping(value = "/getByCode/{code}")
    public JSONObject getByCode(@PathVariable(value = "code") String code){
        TDict tDict = new TDict();
        tDict.setCode(code);
        return JsonUtil.getSuccessJsonObject(baseServiceImpl.selectList(tDict));
    }
}
