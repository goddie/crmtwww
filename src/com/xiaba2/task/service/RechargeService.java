package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IRechargeDao;
import com.xiaba2.task.domain.Recharge;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class RechargeService extends BaseService<Recharge, UUID> {
@Resource
private IRechargeDao rechargeDao;
@Override
protected IBaseDao<Recharge, UUID> getEntityDao() {
return rechargeDao;
}
}