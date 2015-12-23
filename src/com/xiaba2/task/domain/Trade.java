package com.xiaba2.task.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 交易
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_trade")
public class Trade extends BaseUUIDEntity {
	
	
	/**
	 * 雇主
	 */
	@ManyToOne
	private User employer;
	
	/**
	 * 雇员
	 */
	@ManyToOne
	private User employee;
	
	
	/**
	 * 任务
	 */
	@ManyToOne
	private Task task;
	
	/**
	 * 收入
	 */
	@Column
	private float income;
	
	/**
	 * 成交时间
	 */
	@Column
	private Date deal;

	public User getEmployer() {
		return employer;
	}

	public void setEmployer(User employer) {
		this.employer = employer;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public Date getDeal() {
		return deal;
	}

	public void setDeal(Date deal) {
		this.deal = deal;
	}
	
	
}
