package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IForumReplyDao;
import com.xiaba2.task.domain.ForumReply;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ForumReplyService extends BaseService<ForumReply, UUID> {
@Resource
private IForumReplyDao forumReplyDao;
@Override
protected IBaseDao<ForumReply, UUID> getEntityDao() {
return forumReplyDao;
}
}