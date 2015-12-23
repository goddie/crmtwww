package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IBankDao;
import com.xiaba2.task.domain.Bank;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class BankDao extends AbstractHibernateDao<Bank, UUID> implements
IBankDao {
}