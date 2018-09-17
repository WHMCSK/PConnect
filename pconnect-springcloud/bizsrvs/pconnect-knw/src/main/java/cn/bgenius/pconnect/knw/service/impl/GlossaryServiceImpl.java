package cn.bgenius.pconnect.knw.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.knw.entity.TGlossary;
import cn.bgenius.pconnect.knw.mapper.TGlossaryMapper;
import cn.bgenius.pconnect.knw.service.GlossaryService;
import org.springframework.stereotype.Service;

@Service
public class GlossaryServiceImpl extends BaseServiceImpl<TGlossaryMapper, TGlossary> implements GlossaryService {
}
