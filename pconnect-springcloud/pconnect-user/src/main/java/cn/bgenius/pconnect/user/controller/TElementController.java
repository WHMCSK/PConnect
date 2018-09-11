package cn.bgenius.pconnect.user.controller;

import cn.bgenius.pconnect.user.entity.TElement;
import cn.bgenius.pconnect.user.service.TElementService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/17.
 */
@RestController
@RequestMapping("v1/tElement")
public class TElementController extends BaseController<TElementService, TElement,Integer>{

    /**
     * 通过menuId获取元素列表
     * @return
     * @throws RuntimeException
     */
    @ApiOperation(value = "通过menuId获取元素列表" )
    @RequestMapping(value = "getByMenuId/{menuId}", method = RequestMethod.GET)
    public JSONObject getByMenuId(@PathVariable Integer menuId)throws Exception{
        List<TElement> result = baseServiceImpl.getListByMenuId(menuId);
        return JsonUtil.getSuccessJsonObject(result);
    }
}
