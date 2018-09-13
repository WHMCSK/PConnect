package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.common.service.BaseService;
import cn.bgenius.pconnect.user.entity.TUser;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TUserService extends BaseService<TUser>{

    TUser getByUsername(String userName);

    List<TUser> getByKey(String key);

    List<TUser> getLeadersByGroupId(Integer groupId);

    List<TUser> getMembersByGroupId(Integer groupId);

    void register(TUser tUser);
}
