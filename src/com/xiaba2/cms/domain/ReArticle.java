package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 回帖
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_rearticle")
public class ReArticle extends Article {

	/**
	 * 主贴
	 */
	@ManyToOne
	private Article root;
	
	/**
	 * 楼层
	 */
	@Column
	private int floor;
	
	

	public Article getRoot() {
		return root;
	}

	public void setRoot(Article root) {
		this.root = root;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
}
