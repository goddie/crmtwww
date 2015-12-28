package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 附件
 * @author goddie
 *
 */

@Entity
@Table(name = "db_task_attachment")
public class Attachment extends BaseUUIDEntity {

	/**
	 * 任务
	 */
	@ManyToOne
	private Task task;
	
	/**
	 * 交稿
	 */
	@ManyToOne
	private Submit submit;
	
	/**
	 * 文件路径
	 */
	@Column
	private String path;

	public String getPath() {
		return path;
	}

	public Submit getSubmit() {
		return submit;
	}

	public Task getTask() {
		return task;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSubmit(Submit submit) {
		this.submit = submit;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
}
