package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.ITradeDao;
import com.xiaba2.task.domain.Trade;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class TradeService extends BaseService<Trade, UUID> {
@Resource
private ITradeDao tradeDao;
@Override
protected IBaseDao<Trade, UUID> getEntityDao() {
return tradeDao;
}
}