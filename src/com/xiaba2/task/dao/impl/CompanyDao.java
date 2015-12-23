package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.ICompanyDao;
import com.xiaba2.task.domain.Company;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CompanyDao extends AbstractHibernateDao<Company, UUID> implements
ICompanyDao {
}