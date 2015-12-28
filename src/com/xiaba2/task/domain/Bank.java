package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


/**
 * 绑定的银行卡
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_bank")
public class Bank extends BaseUUIDEntity {

	/**
	 * 银行
	 */
	@Column
	private String bankname;
	
	/**
	 * 卡号
	 */
	@Column
	private String accountNo;
	
	/**
	 * 用户
	 */
	@ManyToOne
	private User user;

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
