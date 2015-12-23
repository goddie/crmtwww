package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.task.dao.ITaskTypeDao;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class TaskTypeService extends BaseService<TaskType, UUID> {
	@Resource
	private ITaskTypeDao taskTypeDao;

	@Override
	protected IBaseDao<TaskType, UUID> getEntityDao() {
		return taskTypeDao;
	}
	
	
	/**
	 * 根据父级，获取子集
	 * @param parent
	 * @return
	 */
	@Transactional
	public List<TaskType> getByParent(TaskType parent)
	{
		DetachedCriteria criteria = taskTypeDao.createDetachedCriteria();
		
		if (parent == null) {
			criteria.add(Restrictions.isNull("parent"));
		}else
		{
			criteria.add(Restrictions.isNotNull("parent"));
			criteria.add(Restrictions.eq("parent", parent));
		}
		
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TaskType> list = findByCriteria(criteria);

		return list;
	}
	
}