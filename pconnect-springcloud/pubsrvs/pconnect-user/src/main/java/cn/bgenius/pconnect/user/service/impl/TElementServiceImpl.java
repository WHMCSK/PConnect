package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.user.entity.TElement;
import cn.bgenius.pconnect.user.mapper.TElementMapper;
import cn.bgenius.pconnect.user.po.TElementVo;
import cn.bgenius.pconnect.user.service.TElementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 金飞 on 2017/10/17.
 */
@Service
public class TElementServiceImpl extends BaseServiceImpl<TElementMapper, TElement> implements TElementService {

    @Override
    public List<TElement> getListByMenuId(Integer menuId) {
        return mapper.getListByMenuId(menuId);
    }

    @Override
    public List<TElement> getAuthorityElementsByUsername(String username) {
        return mapper.getAuthorityElementsByUsername(username);
    }

    /*@Cacheable(value = "telement_role", key = "'role_element_'+#role")*/
    public List<TElementVo> getListByRole(String role) {
        List<TElementVo> resultList = new ArrayList<>();
        List<TElement> tElements = mapper.getListByRole(role);
        tElements.forEach(tElement -> {
            TElementVo tElementVo = new TElementVo();
            BeanUtils.copyProperties(tElement,tElementVo);
            resultList.add(tElementVo);
        });
        return resultList;
    }

    @Override
    public String[] getPermissionsByRoles(String[] roles) {
        Set<TElementVo> tElementSet = new HashSet<>();
        for(int i=0;i<roles.length;i++){
            String role = roles[i];
            List<TElementVo> tElements = getListByRole(role);
            tElementSet.addAll(tElements);
        }

        Set<String> permissions = new HashSet<>();
        for (TElementVo tElement : tElementSet) {
            if (StringUtils.isNotEmpty(tElement.getCode())) {
                String permission = tElement.getCode();
                permissions.add(permission);
            }
        }
        return permissions.toArray(new String[permissions.size()]);
    }
}
