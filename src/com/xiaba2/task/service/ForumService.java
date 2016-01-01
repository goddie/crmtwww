package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IForumDao;
import com.xiaba2.task.domain.Forum;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.Page;

@Service
public class ForumService extends BaseService<Forum, UUID> {
	@Resource
	private IForumDao forumDao;

	@Override
	protected IBaseDao<Forum, UUID> getEntityDao() {
		return forumDao;
	}
	
	
	@Transactional
	public List<Forum> getTop(ForumType type, ForumType subType, int count, Boolean hasCover) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("isCheck", 1));
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

		Page<Forum> page = new Page<Forum>();
		page.setPageNo(1);
		page.setPageSize(count);
		page.addOrder("createdDate", "desc");

		page = findPageByCriteria(criteria, page);

		return page.getResult();
	}
}