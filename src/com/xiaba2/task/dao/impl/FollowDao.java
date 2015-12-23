package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IFollowDao;
import com.xiaba2.task.domain.Follow;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class FollowDao extends AbstractHibernateDao<Follow, UUID> implements
IFollowDao {
}