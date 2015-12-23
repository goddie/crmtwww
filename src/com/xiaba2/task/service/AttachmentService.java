package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IAttachmentDao;
import com.xiaba2.task.domain.Attachment;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AttachmentService extends BaseService<Attachment, UUID> {
@Resource
private IAttachmentDao attachmentDao;
@Override
protected IBaseDao<Attachment, UUID> getEntityDao() {
return attachmentDao;
}
}