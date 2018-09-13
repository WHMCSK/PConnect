package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.user.entity.TGroup;
import cn.bgenius.pconnect.user.entity.TUser;
import cn.bgenius.pconnect.user.entity.TUserGroup;
import cn.bgenius.pconnect.user.mapper.TUserMapper;
import cn.bgenius.pconnect.user.service.TGroupService;
import cn.bgenius.pconnect.user.service.TUserGroupService;
import cn.bgenius.pconnect.user.service.TUserService;
import cn.bgenius.pconnect.user.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Service
public class TUserServiceImpl extends BaseServiceImpl<TUserMapper, TUser> implements TUserService {

    @Autowired
    private TUserGroupService tUserGroupService;
    @Autowired
    private TGroupService tGroupService;

    @Override
    public TUser getByUsername(String userName) {
        return mapper.getByUsername(userName);
    }

    @Override
    public List<TUser> getByKey(String key) {
        return mapper.getByKey(key);
    }

    @Override
    public List<TUser> getLeadersByGroupId(Integer groupId) {
        return mapper.getLeadersByGroupId(groupId);
    }

    @Override
    public List<TUser> getMembersByGroupId(Integer groupId) {
        return mapper.getMembersByGroupId(groupId);
    }

    @Override
    public void register(TUser tUser) {
        //获取普通用户角色
        TGroup tGroup = new TGroup();
        tGroup.setCode(UserConstant.ROLE_USER_CODE);
        tGroup = tGroupService.selectOne(tGroup);

        //插入用户
        mapper.insert(tUser);

        //给新注册用户授权普通用户角色
        TUserGroup tUserGroup = new TUserGroup();
        tUserGroup.setUserId(tUser.getId());
        tUserGroup.setGroupId(tGroup.getId());
        tUserGroup.setType(UserConstant.USER_GROUP_TYPE_MEMBER);
        tUserGroupService.insert(tUserGroup);

    }
}
