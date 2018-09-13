package cn.bgenius.pconnect.user.mapper;

import cn.bgenius.pconnect.user.entity.TMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TMenuMapper extends Mapper<TMenu> {
    List<TMenu> getAuthorityMenusByUsername(String username);
}