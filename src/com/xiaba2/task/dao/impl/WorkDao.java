package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IWorkDao;
import com.xiaba2.task.domain.Work;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class WorkDao extends AbstractHibernateDao<Work, UUID> implements
IWorkDao {
}