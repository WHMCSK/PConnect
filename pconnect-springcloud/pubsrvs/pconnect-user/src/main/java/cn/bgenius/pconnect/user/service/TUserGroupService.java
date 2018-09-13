package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.user.entity.TUserGroup;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.service.BaseService;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TUserGroupService extends BaseService<TUserGroup>{

    Boolean saveGroupRelateUsers(Integer groupId, JSONObject param);

    Boolean addByUserIdAndGroupId(TUserGroup tUserGroup);

    Boolean deleteByUserIdAndGroupId(TUserGroup tUserGroup);
}
