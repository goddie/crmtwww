package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IBankDao;
import com.xiaba2.task.domain.Bank;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class BankService extends BaseService<Bank, UUID> {
@Resource
private IBankDao bankDao;
@Override
protected IBaseDao<Bank, UUID> getEntityDao() {
return bankDao;
}
}