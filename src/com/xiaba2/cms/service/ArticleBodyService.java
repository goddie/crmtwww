package com.xiaba2.cms.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IArticleBodyDao;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class ArticleBodyService extends BaseService<ArticleBody, UUID> {

	@Resource
	private IArticleBodyDao articleBodyDao;

	@Override
	protected IBaseDao<ArticleBody, UUID> getEntityDao() {
		return articleBodyDao;
	}

	@Transactional
	public ArticleBody getByArticle(Article article) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("article", article));

		List<ArticleBody> list = findByCriteria(criteria);

		return list.isEmpty() ? null : list.get(0);
	}

}
