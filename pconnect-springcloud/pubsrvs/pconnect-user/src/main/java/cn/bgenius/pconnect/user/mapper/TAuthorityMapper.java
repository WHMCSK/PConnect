package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TAuthority;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TAuthorityMapper extends Mapper<TAuthority> {
    void deleteByGroupId(Integer groupId);

    List<TAuthority> getListByAuthorityId(Integer authorityId);
}