package cn.bgenius.pconnect.user.controller;

import cn.bgenius.pconnect.common.controller.BaseController;
import cn.bgenius.pconnect.user.entity.TGroupType;
import cn.bgenius.pconnect.user.service.TGroupTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 金飞 on 2017/10/17.
 */
@RestController
@RequestMapping("v1/tGroupType")
public class TGroupTypeController extends BaseController<TGroupTypeService, TGroupType,Integer>{

}
