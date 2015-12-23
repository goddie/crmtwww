package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IEvaluationDao;
import com.xiaba2.task.domain.Evaluation;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class EvaluationDao extends AbstractHibernateDao<Evaluation, UUID> implements
IEvaluationDao {
}