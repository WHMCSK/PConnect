package cn.bgenius.pconnect.user.feign;

import cn.bgenius.pconnect.api.vo.user.TUserVo;
import cn.bgenius.pconnect.user.entity.TUser;
import cn.bgenius.pconnect.user.service.TGroupService;
import cn.bgenius.pconnect.user.service.TUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("tUserServiceFeign")
public class TUserServiceFeign {
	private Logger logger = LoggerFactory.getLogger(TUserServiceFeign.class);
	@Autowired
	private TGroupService tGroupService;
	@Autowired
	private TUserService tUserService;
	/**
	 * 通过用户名获取用户的基本信息
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/user/username/{username}", method = RequestMethod.POST)
	public TUserVo getByUsername(@PathVariable("username") String username) {
		TUserVo tUserVo = null;
		try {
			TUser tUser1 = tUserService.getByUsername(username);
			if (tUser1 != null) {
				tUserVo=new TUserVo();
				BeanUtils.copyProperties(tUser1,tUserVo);
				String[] roles = tGroupService.getCodeByUsername(username);
				tUserVo.setRoleList(Arrays.asList(roles));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tUserVo;
	}
}