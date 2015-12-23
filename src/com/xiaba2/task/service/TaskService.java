package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.task.dao.ITaskDao;
import com.xiaba2.task.domain.Task;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.Page;

@Service
public class TaskService extends BaseService<Task, UUID> {
	@Resource
	private ITaskDao taskDao;

	@Override
	protected IBaseDao<Task, UUID> getEntityDao() {
		return taskDao;
	}
	
	

	/**
	 * 获取最新任务
	 * @param count
	 * @param hasCover
	 * @return
	 */
	@Transactional
	public List<Task> getTop(int count, Boolean hasCover) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
 
		if(hasCover)
		{
			criteria.add(Restrictions.isNotNull("thumb"));
			criteria.add(Restrictions.not(Restrictions.eq("thumb", "")));
		}

		Page<Task> page = new Page<Task>();
		page.setPageNo(1);
		page.setPageSize(count);
		page.addOrder("createdDate", "desc");

		page = findPageByCriteria(criteria, page);

		return page.getResult();
	}
}