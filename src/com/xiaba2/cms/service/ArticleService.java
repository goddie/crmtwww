package com.xiaba2.cms.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IArticleDao;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.Page;

@Service
public class ArticleService extends BaseService<Article, UUID> {

	@Resource
	private IArticleDao articleDao;

	@Override
	protected IBaseDao<Article, UUID> getEntityDao() {
		return articleDao;
	}

	/**
	 * 获取最新文章
	 * @param type
	 * @param subType
	 * @param count
	 * @return
	 */
	@Transactional
	public List<Article> getTop(ArticleType type, ArticleType subType, int count, Boolean hasCover) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		if (type != null) {
			criteria.add(Restrictions.eq("type", type));
		}

		if (subType != null) {
			criteria.add(Restrictions.eq("subType", subType));
		}
		
		if(hasCover)
		{
			criteria.add(Restrictions.isNotNull("thumb"));
			criteria.add(Restrictions.not(Restrictions.eq("thumb", "")));
		}

		Page<Article> page = new Page<Article>();
		page.setPageNo(1);
		page.setPageSize(count);
		page.addOrder("createdDate", "desc");

		page = findPageByCriteria(criteria, page);

		return page.getResult();
	}

}
