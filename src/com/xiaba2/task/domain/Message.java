package com.xiaba2.task.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 私信
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_message")
public class Message extends BaseUUIDEntity {

	/**
	 * 内容
	 */
	@Column(length=2000)
	private String content;
	
	/**
	 * 标题
	 */
	@Column
	private String title;
	
	/**
	 * 发送方
	 */
	@ManyToOne
	private User from;
	
	/**
	 * 接收方
	 */
	@ManyToOne
	private User sendTo;
	
	
	/**
	 * 阅读状态
	 */
	@Column
	private int status;
	


	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public User getFrom() {
		return from;
	}





	public void setFrom(User from) {
		this.from = from;
	}





	public User getSendTo() {
		return sendTo;
	}





	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}





	public int getStatus() {
		return status;
	}





	public void setStatus(int status) {
		this.status = status;
	}





	public String getContent() {
		return content;
	}


 


	public void setContent(String content) {
		this.content = content;
	}


 
}
