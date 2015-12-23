package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.ISubmitDao;
import com.xiaba2.task.domain.Submit;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.util.ListUtil;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class SubmitService extends BaseService<Submit, UUID> {
	@Resource
	private ISubmitDao submitDao;

	@Override
	protected IBaseDao<Submit, UUID> getEntityDao() {
		return submitDao;
	}
	
	/**
	 * 用户是否已交稿
	 * @param user
	 * @param task
	 * @return
	 */
	@Transactional
	public Submit getByTaskUser(User user,Task task)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("task", task));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<Submit> list = findByCriteria(criteria);
		
		return ListUtil.isEmpty(list)?null:list.get(0);
	}
}