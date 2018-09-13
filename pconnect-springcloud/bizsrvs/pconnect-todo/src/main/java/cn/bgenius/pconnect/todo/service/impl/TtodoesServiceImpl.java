package cn.bgenius.pconnect.todo.service.impl;

import cn.bgenius.pconnect.todo.entity.TTodoes;
import cn.bgenius.pconnect.todo.mapper.TTodoesMapper;
import cn.bgenius.pconnect.todo.service.TtodoesService;
import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TtodoesServiceImpl extends BaseServiceImpl<TTodoesMapper, TTodoes> implements TtodoesService {
}
