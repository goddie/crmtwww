package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 支付记录
 * @author goddie
 *
 */

@Entity
@Table(name = "db_task_payrecord")
public class PayRecord extends BaseUUIDEntity {

	@ManyToOne
	private Order order;
	
	/**
	 * 付款人
	 */
	@ManyToOne
	private User user;
	
	/**
	 * 收款人
	 */
	@ManyToOne
	private User toUser;
	
	/**
	 * 总金额
	 */
	@Column
	private float total;
	
	@Column
	private String title;
	
	/**
	 * 支付来源
	 */
	@Column
	private String comeFrom;
	
	@Column 
	private String sendTo;
	
	
	

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

 

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

 
	
}
