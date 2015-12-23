package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IRechargeDao;
import com.xiaba2.task.domain.Recharge;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class RechargeDao extends AbstractHibernateDao<Recharge, UUID> implements
IRechargeDao {
}