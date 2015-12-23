package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;
/**
 * 提现
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_cash")
public class Cash extends BaseUUIDEntity {

	@ManyToOne
	private User user;
	
	/**
	 * 提现金额
	 */
	@Column
	private float total;
	
	/**
	 * 充值方式
	 */
	@Column
	private int cashType;
	
	/**
	 * 线下银行
	 */
	@Column
	private String bank;
	
	
	/**
	 * 银行流水号
	 */
	@Column
	private String bankNo;
	
	/**
	 * 支付金额
	 */
	@Column
	private float bankTotal;
	
	
	/**
	 * 银行回执单
	 */
	@Column
	private String bankImage;
	
	
	/**
	 * 备注
	 */
	@Column(length=2000)
	private String content;
	
	


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


	public int getCashType() {
		return cashType;
	}


	public void setCashType(int cashType) {
		this.cashType = cashType;
	}


	public String getBank() {
		return bank;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}


	public String getBankNo() {
		return bankNo;
	}


	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}


	public float getBankTotal() {
		return bankTotal;
	}


	public void setBankTotal(float bankTotal) {
		this.bankTotal = bankTotal;
	}


	public String getBankImage() {
		return bankImage;
	}


	public void setBankImage(String bankImage) {
		this.bankImage = bankImage;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	 
	
	
	
}
