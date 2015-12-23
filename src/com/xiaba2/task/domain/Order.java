package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;
@Entity
@Table(name = "db_task_order")
public class Order extends BaseUUIDEntity  {
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User user;
	
	/**
	 * 收款方
	 */
	@ManyToOne
	private User toUser;
	
	@Column
	private float count;
	
	@Column
	private float price;
	
	@Column
	private float total;
	
	/**
	 * 0无效 1待付款 2已付款  4已服务 5交易关闭
	 */
	@Column
	private int status;
	
	/**
	 * 订单号
	 * yyyyMMddHHmmss
	 */
	@Column
	private long orderNum;
	
	/**
	 * 1=已支付 0=未支付
	 */
	@Column
	private int isPay;
	
	
	

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

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

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
