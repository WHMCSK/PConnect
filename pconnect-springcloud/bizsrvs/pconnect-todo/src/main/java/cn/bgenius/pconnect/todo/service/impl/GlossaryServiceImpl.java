package cn.bgenius.pconnect.todo.service.impl;

import cn.bgenius.pconnect.todo.entity.TGlossary;
import cn.bgenius.pconnect.todo.mapper.TGlossaryMapper;
import cn.bgenius.pconnect.todo.service.GlossaryService;
import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GlossaryServiceImpl extends BaseServiceImpl<TGlossaryMapper, TGlossary> implements GlossaryService {
}
