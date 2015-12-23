package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IForumTypeDao;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ForumTypeDao extends AbstractHibernateDao<ForumType, UUID> implements
IForumTypeDao {
}