package com.xiaba2.task.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IFollowDao;
import com.xiaba2.task.domain.Follow;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.gen.EnumSet.CheckStatus;
import com.xiaba2.util.ListUtil;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class FollowService extends BaseService<Follow, UUID> {
	@Resource
	private IFollowDao followDao;

	@Override
	protected IBaseDao<Follow, UUID> getEntityDao() {
		return followDao;
	}
	
	/**
	 * 已关注
	 * @param host
	 * @param guest
	 * @return
	 */
	@Transactional
	public Boolean hasFollow(User host,User guest)
	{
		Boolean rs = false;
		
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("host", host));
		criteria.add(Restrictions.eq("guest", guest));
		
		List<Follow> list = findByCriteria(criteria);

		if(!ListUtil.isEmpty(list))
		{
			rs = true;
		}
		
		return rs;
	}
}