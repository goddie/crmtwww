package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IFollowDao;
import com.xiaba2.task.domain.Follow;
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
}