package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IForumReplyDao;
import com.xiaba2.task.domain.ForumReply;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ForumReplyDao extends AbstractHibernateDao<ForumReply, UUID> implements
IForumReplyDao {
}