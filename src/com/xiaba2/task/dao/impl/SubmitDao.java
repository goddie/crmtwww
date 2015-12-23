package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ISubmitDao;
import com.xiaba2.task.domain.Submit;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class SubmitDao extends AbstractHibernateDao<Submit, UUID> implements
ISubmitDao {
}