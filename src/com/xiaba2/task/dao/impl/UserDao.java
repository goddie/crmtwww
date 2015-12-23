package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IUserDao;
import com.xiaba2.task.domain.User;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserDao extends AbstractHibernateDao<User, UUID> implements
IUserDao {
}