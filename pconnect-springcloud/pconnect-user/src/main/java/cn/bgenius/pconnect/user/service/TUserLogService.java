package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.common.service.BaseService;
import cn.bgenius.pconnect.user.entity.TUserLog;

import java.util.List;

/**
 * Created by fier on 2017/11/28.
 */
public interface TUserLogService extends BaseService<TUserLog>{
    List<TUserLog> listByPageNewRecord();
}
