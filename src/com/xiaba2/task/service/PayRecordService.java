package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IPayRecordDao;
import com.xiaba2.task.domain.PayRecord;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class PayRecordService extends BaseService<PayRecord, UUID> {
@Resource
private IPayRecordDao payRecordDao;
@Override
protected IBaseDao<PayRecord, UUID> getEntityDao() {
return payRecordDao;
}
}