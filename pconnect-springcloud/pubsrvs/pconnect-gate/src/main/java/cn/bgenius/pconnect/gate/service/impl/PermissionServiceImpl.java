package cn.bgenius.pconnect.gate.service.impl;

import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.api.vo.authority.PermissionInfo;
import cn.bgenius.pconnect.gate.feign.IUserServiceFeignClient;
import cn.bgenius.pconnect.gate.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    private IUserServiceFeignClient iUserService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        logger.info("PConnect:hasPermission");
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        logger.info("PConnect:grantedAuthorityList:{}",JSONObject.toJSON(grantedAuthorityList));
        boolean hasPermission = false;

        if (principal != null) {
            Set<String> roleSet = new HashSet<>();
            grantedAuthorityList.forEach(grantedAuthority -> roleSet.add(grantedAuthority.getAuthority()));
            String roles = StringUtils.join(roleSet,";");
            Set<PermissionInfo> permissionInfos = iUserService.findPermissionInfoByRoles(roles);
            logger.info("PConnect:PersissionInfos：{}", JSONObject.toJSON(permissionInfos));
            logger.info("PConnect:request.getRequestURI()：{}", request.getRequestURI());
            for (PermissionInfo permissionInfo : permissionInfos) {
                if (antPathMatcher.match(permissionInfo.getUri(), request.getRequestURI())
                        && request.getMethod().equalsIgnoreCase(permissionInfo.getMethod())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        logger.info("PConnect:hasPermission:{}", hasPermission);
        return hasPermission;
    }
}
