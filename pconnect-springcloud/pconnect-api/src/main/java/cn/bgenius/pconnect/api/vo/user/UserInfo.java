package cn.bgenius.pconnect.api.vo.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fier on 2017/11/22.
 */
@Data
public class UserInfo implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private List<String> roleList = new ArrayList<>();

    public UserInfo(TUserVo tUser) {
        this.username = tUser.getUsername();
        this.password = tUser.getPassword();
        roleList = tUser.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.forEach(role ->authorityList.add(new SimpleGrantedAuthority(role)));
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
