package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.ICashDao;
import com.xiaba2.task.domain.Cash;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class CashService extends BaseService<Cash, UUID> {
@Resource
private ICashDao cashDao;
@Override
protected IBaseDao<Cash, UUID> getEntityDao() {
return cashDao;
}
}