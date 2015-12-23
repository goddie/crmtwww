package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ITaskDao;
import com.xiaba2.task.domain.Task;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TaskDao extends AbstractHibernateDao<Task, UUID> implements
ITaskDao {
}