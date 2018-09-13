package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.common.service.BaseService;
import cn.bgenius.pconnect.user.entity.TElement;
import cn.bgenius.pconnect.user.po.TElementVo;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TElementService extends BaseService<TElement>{

    List<TElement> getListByMenuId(Integer menuId);

    List<TElement> getAuthorityElementsByUsername(String username);

    List<TElementVo> getListByRole(String role);

    String[] getPermissionsByRoles(String[] roles);
}
