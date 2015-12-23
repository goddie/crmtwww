package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IEvaluationDao;
import com.xiaba2.task.domain.Evaluation;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class EvaluationService extends BaseService<Evaluation, UUID> {
@Resource
private IEvaluationDao evaluationDao;
@Override
protected IBaseDao<Evaluation, UUID> getEntityDao() {
return evaluationDao;
}
}