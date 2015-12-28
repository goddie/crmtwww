package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 交稿
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_submit")
public class Submit extends BaseUUIDEntity {

	/**
	 * 任务
	 */
	@ManyToOne
	private Task task;

	/**
	 * 提交人
	 */
	@ManyToOne
	private User user;

	/**
	 * 描述
	 */
	@Column(length = 2000)
	private String content;

	/**
	 * 交稿结果
	 */
	@Column
	private int result;

	@Column
	private String QQ;

	/**
	 * 手机
	 */
	@Column
	private String phone;

	/**
	 * 座机
	 */
	@Column
	private String tel;

	@Column
	private String email;

	/**
	 * 联系人
	 */
	@Column
	private String name;

	/**
	 * 性别
	 */
	@Column
	private String sex;
	

	/**
	 * 一级分类
	 * 1 单人悬赏
	 * 2 计件悬赏
	 * 3 项目招标
	 * 4 项目交付
	 * 5 直接雇佣

	 */
	@Column
	private int topType;
	
	@Column
	private int status;
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTopType() {
		return topType;
	}

	public void setTopType(int topType) {
		this.topType = topType;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public int getResult() {
		return result;
	}

	public Task getTask() {
		return task;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
