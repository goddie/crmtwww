package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IFeedbackDao;
import com.xiaba2.task.domain.Feedback;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class FeedbackService extends BaseService<Feedback, UUID> {
@Resource
private IFeedbackDao feedbackDao;
@Override
protected IBaseDao<Feedback, UUID> getEntityDao() {
return feedbackDao;
}
}