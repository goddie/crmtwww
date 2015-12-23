package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ICollectDao;
import com.xiaba2.task.domain.Collect;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CollectDao extends AbstractHibernateDao<Collect, UUID> implements
ICollectDao {
}