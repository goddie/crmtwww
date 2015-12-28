package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


/**
 * 粉丝好友
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_follow")
public class Follow extends BaseUUIDEntity {

	/**
	 * 主人
	 */
	@ManyToOne
	private User host;
	
	/**
	 * 客人
	 */
	@ManyToOne
	private User guest;
	
	/**
	 * 是否朋友
	 */
	@Column
	private int isFriend;

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public int getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}
	
	
}
