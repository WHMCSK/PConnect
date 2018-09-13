package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.user.entity.TAuthority;
import cn.bgenius.pconnect.user.mapper.TAuthorityMapper;
import cn.bgenius.pconnect.user.service.TAuthorityService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.constant.CommonConstant;
import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/17.
 */
@Service
public class TAuthorityServiceImpl extends BaseServiceImpl<TAuthorityMapper, TAuthority> implements TAuthorityService {

    @Override
    @Transactional
    public boolean saveBatch(Integer groupId, JSONArray menuIds, JSONArray elementIds) {
        mapper.deleteByGroupId(groupId);

        for(int i=0;i<menuIds.size();i++){
            Integer menuId = menuIds.getInteger(i);
            TAuthority tAuthority = new TAuthority();
            tAuthority.setAuthorityId(groupId);
            tAuthority.setAuthorityType(CommonConstant.AUTHORITY_TYPE_GROUP);
            tAuthority.setResourceId(menuId);
            tAuthority.setResourceType(CommonConstant.RESOURCE_TYPE_MENU);
            mapper.insert(tAuthority);
        }

        for(int i=0;i<elementIds.size();i++){
            Integer elementId = elementIds.getInteger(i);
            TAuthority tAuthority = new TAuthority();
            tAuthority.setAuthorityId(groupId);
            tAuthority.setAuthorityType(CommonConstant.AUTHORITY_TYPE_GROUP);
            tAuthority.setResourceId(elementId);
            tAuthority.setResourceType(CommonConstant.RESOURCE_TYPE_ELEMENT);
            mapper.insert(tAuthority);
        }
        return true;
    }

    @Override
    public JSONObject getAuthority(Integer groupId) {
        JSONObject result = new JSONObject();
        JSONArray menuIds = new JSONArray();
        JSONArray elementIds = new JSONArray();
        List<TAuthority> tAuthorityList = mapper.getListByAuthorityId(groupId);
        for(TAuthority tAuthority:tAuthorityList){
            if(tAuthority.getResourceType().equals(CommonConstant.RESOURCE_TYPE_MENU)){
                menuIds.add(tAuthority.getResourceId());
            }else if(tAuthority.getResourceType().equals(CommonConstant.RESOURCE_TYPE_ELEMENT)){
                elementIds.add(tAuthority.getResourceId());
            }
        }
        result.put("menuIds",menuIds);
        result.put("elementIds",elementIds);
        return result;
    }
}
