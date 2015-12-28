package com.xiaba2.task.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 收藏
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_task_collect")
public class Collect extends BaseUUIDEntity {

	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User user;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
