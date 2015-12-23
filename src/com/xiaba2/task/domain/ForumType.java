package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_task_forumtype")
public class ForumType extends BaseUUIDEntity {
	@Column
	private String name;

	@ManyToOne
	private ForumType parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ForumType getParent() {
		return parent;
	}

	public void setParent(ForumType parent) {
		this.parent = parent;
	}

}
