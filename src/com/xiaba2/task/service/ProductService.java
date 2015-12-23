package com.xiaba2.task.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.task.dao.IProductDao;
import com.xiaba2.task.domain.Product;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ProductService extends BaseService<Product, UUID> {
@Resource
private IProductDao productDao;
@Override
protected IBaseDao<Product, UUID> getEntityDao() {
return productDao;
}
}