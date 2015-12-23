package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IForumTypeDao;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ForumTypeService extends BaseService<ForumType, UUID> {
@Resource
private IForumTypeDao forumTypeDao;
@Override
protected IBaseDao<ForumType, UUID> getEntityDao() {
return forumTypeDao;
}
}