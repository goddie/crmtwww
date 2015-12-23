package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IWorkDao;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.domain.Work;

import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;


@Service
public class WorkService extends BaseService<Work, UUID> {
	@Resource
	private IWorkDao workDao;

	@Override
	protected IBaseDao<Work, UUID> getEntityDao() {
		return workDao;
	}
	
	/**
	 * 用户作品
	 * @param user
	 * @return
	 */
	@Transactional
	public List<Work> getUserWork(User user)
	{
//		Page<Work> page = new Page<Work>();
//		page.setPageSize(HttpUtil.PAGE_SIZE);
//		page.setPageNo(p);
//		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));

		List<Work> list = findByCriteria(criteria);

		return list;

	}
}