package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.task.dao.IUserDao;
import com.xiaba2.task.domain.User;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

/**
 * @author Administrator
 *
 */
@Service
public class UserService extends BaseService<User, UUID> {
	@Resource
	private IUserDao userDao;

	@Override
	protected IBaseDao<User, UUID> getEntityDao() {
		return userDao;
	}

	/**
	 * 根据Member获取
	 * 
	 * @param member
	 * @return
	 */
	@Transactional
	public User getByMember(Member member) {
		DetachedCriteria criteria = userDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("member", member));

		List<User> list = findByCriteria(criteria);

		return list == null ? null : list.get(0);
	}
	
	/**
	 * 根据用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	@Transactional
	public User getByUsername(String username) {
		DetachedCriteria criteria = userDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("username", username.trim()));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<User> list = findByCriteria(criteria);

		if (list == null || list.size()==0) {
			return null;
		}

		return list.get(0);

	}
}