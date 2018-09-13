package cn.bgenius.pconnect.user.controller;

import cn.bgenius.pconnect.user.entity.TUserLog;
import cn.bgenius.pconnect.user.service.TUserLogService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.bgenius.pconnect.common.constant.CommonConstant;
import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.common.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fier on 2017/11/28.
 */
@RestController
@RequestMapping("v1/tUserLog")
public class TUserLogController extends BaseController<TUserLogService, TUserLog,Integer> {

    @RequestMapping(value = "/listByPage",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listByPage(Integer pageNum, Integer pageSize){

        pageNum = pageNum == null? CommonConstant.PAGE_NUM:pageNum;
        pageSize = pageSize == null?CommonConstant.PAGE_SIZE:pageSize;

        PageHelper.startPage(pageNum, pageSize);
        List<TUserLog> tMenuList = baseServiceImpl.listByPageNewRecord();
        PageInfo page = new PageInfo(tMenuList);
        return JsonUtil.getSuccessJsonObject(page);
    }
}
