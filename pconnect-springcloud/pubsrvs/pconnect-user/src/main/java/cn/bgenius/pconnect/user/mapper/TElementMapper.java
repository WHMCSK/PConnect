package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TElement;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TElementMapper extends Mapper<TElement> {
    List<TElement> getListByMenuId(Integer menuId);

    List<TElement> getAuthorityElementsByUsername(String username);

    List<TElement> getListByRole(String role);
}