package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IPayRecordDao;
import com.xiaba2.task.domain.PayRecord;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class PayRecordDao extends AbstractHibernateDao<PayRecord, UUID> implements
IPayRecordDao {
}