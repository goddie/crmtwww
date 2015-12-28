package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;



/**
 * 作品
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_task_work")
public class Work extends BaseUUIDEntity {

	@ManyToOne
	private User user;
	
	@Column(length=2000)
	private String content;
	
	@Column
	private String thumb;
	
	@Column
	private String title;

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	
	
}
