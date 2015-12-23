package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.task.dao.ICompanyDao;
import com.xiaba2.task.domain.Company;
import com.xiaba2.task.domain.User;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class CompanyService extends BaseService<Company, UUID> {
	@Resource
	private ICompanyDao companyDao;

	@Override
	protected IBaseDao<Company, UUID> getEntityDao() {
		return companyDao;
	}

	/**
	 * 根据用户获得
	 * @param user
	 * @return
	 */
	@Transactional
	public Company getByUser(User user) {
		if (user == null) {
			return null;
		}
		DetachedCriteria criteria = companyDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));

		List<Company> list = findByCriteria(criteria);

		return list == null ? null : list.get(0);
	}
}