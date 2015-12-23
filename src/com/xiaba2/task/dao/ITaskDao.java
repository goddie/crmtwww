package com.xiaba2.task.dao;
import java.util.UUID;
import com.xiaba2.task.domain.Task;
import com.xiaba2.core.IBaseDao;
public interface ITaskDao extends IBaseDao<Task, UUID> {
}