package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;
/**
 * 充值
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_recharge")
public class Recharge extends BaseUUIDEntity {

	/**
	 * 充值金额
	 */
	@Column
	private float total;
	
	/**
	 * 充值方式
	 */
	@Column
	private int chargeType;
	
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
	
	@ManyToOne
	private User user;

	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getBank() {
		return bank;
	}


	public String getBankImage() {
		return bankImage;
	}


	public String getBankNo() {
		return bankNo;
	}


	public float getBankTotal() {
		return bankTotal;
	}


	public int getChargeType() {
		return chargeType;
	}


	public String getContent() {
		return content;
	}


	public float getTotal() {
		return total;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}


	public void setBankImage(String bankImage) {
		this.bankImage = bankImage;
	}


	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}


	public void setBankTotal(float bankTotal) {
		this.bankTotal = bankTotal;
	}


	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
}
