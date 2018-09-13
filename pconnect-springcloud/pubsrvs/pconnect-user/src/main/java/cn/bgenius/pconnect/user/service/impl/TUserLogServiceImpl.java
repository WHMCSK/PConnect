package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.user.entity.TUserLog;
import cn.bgenius.pconnect.user.mapper.TUserLogMapper;
import cn.bgenius.pconnect.user.service.TUserLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fier on 2017/11/28.
 */
@Service
public class TUserLogServiceImpl extends BaseServiceImpl<TUserLogMapper, TUserLog> implements TUserLogService {
    @Override
    public List<TUserLog> listByPageNewRecord() {
        return mapper.listByPageNewRecord();
    }
}
