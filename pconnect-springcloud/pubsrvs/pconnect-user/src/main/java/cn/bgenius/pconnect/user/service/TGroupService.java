package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.common.service.BaseService;
import cn.bgenius.pconnect.user.entity.TGroup;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TGroupService extends BaseService<TGroup>{

    List<TGroup> getListBygroupTypeId(Integer groupTypeId);

    String[] getCodeByUsername(String username);
}
