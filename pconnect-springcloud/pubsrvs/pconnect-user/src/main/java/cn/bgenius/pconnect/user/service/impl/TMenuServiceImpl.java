package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.user.entity.TMenu;
import cn.bgenius.pconnect.user.mapper.TMenuMapper;
import cn.bgenius.pconnect.user.service.TMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Service
public class TMenuServiceImpl extends BaseServiceImpl<TMenuMapper, TMenu> implements TMenuService {

    @Override
    public List<TMenu> getAuthorityMenusByUsername(String username) {
        return mapper.getAuthorityMenusByUsername(username);
    }
}
