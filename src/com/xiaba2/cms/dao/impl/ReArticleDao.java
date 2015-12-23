package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IReArticleDao;
import com.xiaba2.cms.domain.ReArticle;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ReArticleDao extends AbstractHibernateDao<ReArticle, UUID> implements
IReArticleDao {
}