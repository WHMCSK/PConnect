package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TUserLog;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TUserLogMapper extends Mapper<TUserLog> {
    List<TUserLog> listByPageNewRecord();
}