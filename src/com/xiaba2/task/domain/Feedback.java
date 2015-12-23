package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 留言
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_feedback")
public class Feedback extends BaseUUIDEntity {

	/**
	 * 内容
	 */
	@Column(length = 2000)
	private String content;

	/**
	 * 发送方
	 */
	@ManyToOne
	private User author;

	/**
	 * 阅读状态
	 */
	@Column
	private int status;

	/**
	 * 根留言
	 */
	@ManyToOne
	private Feedback root;

	/**
	 * 任务
	 */
	@ManyToOne
	private Task task;

	/**
	 * 商品
	 */
	@ManyToOne
	private Product product;
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Feedback getRoot() {
		return root;
	}

	public void setRoot(Feedback root) {
		this.root = root;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
