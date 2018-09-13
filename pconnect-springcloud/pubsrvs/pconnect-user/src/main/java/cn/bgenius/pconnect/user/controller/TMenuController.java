package cn.bgenius.pconnect.user.controller;

import cn.bgenius.pconnect.user.entity.TMenu;
import cn.bgenius.pconnect.user.po.TMenuTree;
import cn.bgenius.pconnect.user.service.TMenuService;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import cn.bgenius.pconnect.common.util.TreeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 金飞 on 2017/10/17.
 */
@RestController
@RequestMapping("v1/tMenu")
public class TMenuController extends BaseController<TMenuService, TMenu,Integer>{

    /**
     * 获取完整树形结构
     * @return
     * @throws RuntimeException
     */
    @ApiOperation(value = "获取完整树形结构" )
    @RequestMapping(value = "allTree", method = RequestMethod.GET)
    public JSONObject allTree()throws Exception{
        List<TMenuTree> tMenuTreeList = new ArrayList<>();
        List<TMenu> menuList = baseServiceImpl.selectListAll();
        for(TMenu tMenu:menuList){
            TMenuTree tMenuTree = new TMenuTree();
            BeanUtils.copyProperties(tMenu,tMenuTree);
            tMenuTreeList.add(tMenuTree);
        }
        List<TMenuTree> result = TreeUtil.buildByRecursive(tMenuTreeList,-1);
        return JsonUtil.getSuccessJsonObject(result);
    }

}
