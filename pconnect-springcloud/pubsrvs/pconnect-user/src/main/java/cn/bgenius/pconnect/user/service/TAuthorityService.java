package cn.bgenius.pconnect.user.service;

import cn.bgenius.pconnect.user.entity.TAuthority;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.service.BaseService;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface TAuthorityService extends BaseService<TAuthority>{

    boolean saveBatch(Integer groupId, JSONArray menuIds, JSONArray elementIds);

    JSONObject getAuthority(Integer groupId);
}
