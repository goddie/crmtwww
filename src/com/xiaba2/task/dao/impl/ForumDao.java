package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IForumDao;
import com.xiaba2.task.domain.Forum;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ForumDao extends AbstractHibernateDao<Forum, UUID> implements
IForumDao {
}