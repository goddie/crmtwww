package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 服务分类
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_tasktype")
public class TaskType extends BaseUUIDEntity {

	
	/**
	 * 父级
	 */
	@ManyToOne
	private TaskType parent;
	
	/**
	 * 名称
	 */
	@Column
	private String name;

	/**
	 * 排序数字
	 */
	@Column
	private int sortRank;

	public String getName() {
		return name;
	}

	public TaskType getParent() {
		return parent;
	}

	public int getSortRank() {
		return sortRank;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(TaskType parent) {
		this.parent = parent;
	}

	public void setSortRank(int sortRank) {
		this.sortRank = sortRank;
	}

 
	
 
}
