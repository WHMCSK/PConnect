package cn.bgenius.pconnect.knw.service.impl;

import cn.bgenius.pconnect.common.service.impl.BaseServiceImpl;
import cn.bgenius.pconnect.knw.entity.TArticleCollect;
import cn.bgenius.pconnect.knw.mapper.TArticleCollectMapper;
import cn.bgenius.pconnect.knw.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<TArticleCollectMapper, TArticleCollect> implements ArticleService {
}
