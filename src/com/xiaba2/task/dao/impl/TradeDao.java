package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ITradeDao;
import com.xiaba2.task.domain.Trade;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TradeDao extends AbstractHibernateDao<Trade, UUID> implements
ITradeDao {
}