package com.xiaba2.task.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.task.dao.IProductDao;
import com.xiaba2.task.domain.Product;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ProductDao extends AbstractHibernateDao<Product, UUID> implements
IProductDao {
}