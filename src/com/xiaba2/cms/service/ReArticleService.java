package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IReArticleDao;
import com.xiaba2.cms.domain.ReArticle;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ReArticleService extends BaseService<ReArticle, UUID> {
@Resource
private IReArticleDao reArticleDao;
@Override
protected IBaseDao<ReArticle, UUID> getEntityDao() {
return reArticleDao;
}
}