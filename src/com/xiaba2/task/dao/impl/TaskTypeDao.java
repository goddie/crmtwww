package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ITaskTypeDao;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TaskTypeDao extends AbstractHibernateDao<TaskType, UUID> implements
ITaskTypeDao {
}