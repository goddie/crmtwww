package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.ICollectDao;
import com.xiaba2.task.domain.Collect;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class CollectService extends BaseService<Collect, UUID> {
@Resource
private ICollectDao collectDao;
@Override
protected IBaseDao<Collect, UUID> getEntityDao() {
return collectDao;
}
}