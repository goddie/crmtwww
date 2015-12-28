package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 评价
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_evaluation")
public class Evaluation extends BaseUUIDEntity {
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
	 * 好评
	 */
	@Column
	private int goodPost;
	
	/**
	 * 差评
	 */
	@Column
	private int badPost;
	
	/**
	 * 中评
	 */
	@Column
	private int midPost;
	
	/**
	 * 工作质量
	 */
	@Column
	private float quality;
	
	/**
	 * 工作速度
	 */
	@Column
	private float speed;
	
	/**
	 * 工作态度
	 */
	@Column
	private float attitude;
	
	/**
	 * 评价内容
	 */
	@Column(length=2000)
	private String content;
	
	

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public float getAttitude() {
		return attitude;
	}

 
	public int getBadPost() {
		return badPost;
	}


	public User getEmployee() {
		return employee;
	}


	public User getEmployer() {
		return employer;
	}


	public int getGoodPost() {
		return goodPost;
	}


	public int getMidPost() {
		return midPost;
	}


	public float getQuality() {
		return quality;
	}


	public float getSpeed() {
		return speed;
	}

	public void setAttitude(float attitude) {
		this.attitude = attitude;
	}

 
	public void setBadPost(int badPost) {
		this.badPost = badPost;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public void setEmployer(User employer) {
		this.employer = employer;
	}

 

	public void setGoodPost(int goodPost) {
		this.goodPost = goodPost;
	}

	public void setMidPost(int midPost) {
		this.midPost = midPost;
	}

 

	public void setQuality(float quality) {
		this.quality = quality;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
