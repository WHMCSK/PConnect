package cn.bgenius.pconnect.user.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.user.entity.TGroup;
import cn.bgenius.pconnect.user.mapper.TGroupMapper;
import cn.bgenius.pconnect.user.service.TGroupService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 金飞 on 2017/10/17.
 */
@Service
public class TGroupServiceImpl extends BaseServiceImpl<TGroupMapper, TGroup> implements TGroupService {

    @Override
    public List<TGroup> getListBygroupTypeId(Integer groupTypeId) {
        List<TGroup> resultList = mapper.getListByGroupTypeId(groupTypeId);
        return resultList;
    }

    @Override
    public String[] getCodeByUsername(String username) {
        Set<String> set = new HashSet<>();
        List<TGroup> tGroupList = mapper.getListByUsername(username);
        tGroupList.forEach(tGroup -> set.add(tGroup.getCode()));
        return set.toArray(new String[set.size()]);
    }
}
