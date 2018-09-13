package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TUserMapper extends Mapper<TUser> {
    TUser getByUsername(String userName);

    List<TUser> getByKey(String key);

    List<TUser> getLeadersByGroupId(Integer groupId);

    List<TUser> getMembersByGroupId(Integer groupId);
}