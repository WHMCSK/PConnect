package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.common.service.BaseService;
import cn.bgenius.pconnect.user.entity.TMenu;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TMenuService extends BaseService<TMenu>{

    List<TMenu> getAuthorityMenusByUsername(String username);
}
