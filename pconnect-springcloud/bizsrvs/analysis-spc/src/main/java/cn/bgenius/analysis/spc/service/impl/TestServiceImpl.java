package cn.bgenius.analysis.spc.service.impl;

import cn.bgenius.analysis.spc.entity.TTest;
import cn.bgenius.analysis.spc.mapper.TTestMapper;
import cn.bgenius.analysis.spc.service.TestService;
import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl  extends BaseServiceImpl<TTestMapper, TTest> implements TestService {
}
