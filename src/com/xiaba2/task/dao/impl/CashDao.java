package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ICashDao;
import com.xiaba2.task.domain.Cash;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CashDao extends AbstractHibernateDao<Cash, UUID> implements
ICashDao {
}