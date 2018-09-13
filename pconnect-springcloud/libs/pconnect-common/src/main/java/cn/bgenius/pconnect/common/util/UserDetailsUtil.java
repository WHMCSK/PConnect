package cn.bgenius.pconnect.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by 金飞 on 2017/10/23.
 */
public class UserDetailsUtil {
    /*
 * 获取当前用户
 *
 * @return
 */
    public static UserDetails getCurrentUser() {
        UserDetails user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;
        if (authentication != null) {
            principal = authentication.getPrincipal();
        }
        if (principal != null && principal instanceof UserDetails) {
            user = (UserDetails) principal;
        }
        return user;
    }
}
