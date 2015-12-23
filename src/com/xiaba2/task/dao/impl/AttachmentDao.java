package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IAttachmentDao;
import com.xiaba2.task.domain.Attachment;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AttachmentDao extends AbstractHibernateDao<Attachment, UUID> implements
IAttachmentDao {
}