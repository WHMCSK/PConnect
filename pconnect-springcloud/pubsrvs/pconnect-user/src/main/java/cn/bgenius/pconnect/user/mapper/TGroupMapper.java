package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TGroup;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TGroupMapper extends Mapper<TGroup> {
    List<TGroup> getListByGroupTypeId(Integer groupTypeId);

    List<TGroup> getListByUsername(String username);
}