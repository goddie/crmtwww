package com.xiaba2.task.domain;

import java.util.Date;

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
@Table(name = "db_task_task")
public class Task extends BaseUUIDEntity {

	/**
	 * 标题
	 */
	@Column
	private String title;

	/**
	 * 一级
	 */
	@ManyToOne
	private TaskType parentType;

	/**
	 * 二级
	 */
	@ManyToOne
	private TaskType subType;

	/**
	 * 悬赏
	 */
	@Column
	private float bounty;

	/**
	 * 托管金额
	 */
	@Column
	private float deposit;

	/**
	 * 内容
	 */
	@Column(length = 2000)
	private String content;

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
	 * 悬赏模式
	 */
	@Column
	private int bountyMode;

	/**
	 * 悬赏价格
	 */
	@Column
	private float bountyPrice;

	/**
	 * 悬赏件数
	 */
	@Column
	private int bountyCount;

	/**
	 * 截止日期
	 */
	@Column
	private Date endDate;

	/**
	 * 流程
	 */
	@Column
	private int status;

	/**
	 * 作者
	 */
	@ManyToOne
	private User user;

	/**
	 * 中标人
	 */
	@ManyToOne
	private Submit win;

	/**
	 * 投稿数
	 */
	@Column
	private int submitCount;

	/**
	 * 访问人数
	 */
	@Column
	private int visit;

	/**
	 * 一级分类 1 单人悬赏 2 计件悬赏 3 项目招标 4 项目交付 5 直接雇佣
	 * 
	 */
	@Column
	private int topType;

	/**
	 * 封面
	 */
	@Column
	private String thumb;

	/**
	 * 是否已充值
	 */
	@Column
	private int isPay;
	
	/**
	 * 是否免费
	 */
	@Column
	private int isFree;
	
	@Column
	private int isCheck;
	
	
	 

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	public float getBounty() {
		return bounty;
	}

	public int getBountyCount() {
		return bountyCount;
	}

	public int getBountyMode() {
		return bountyMode;
	}

	public float getBountyPrice() {
		return bountyPrice;
	}

	public String getContent() {
		return content;
	}

	public float getDeposit() {
		return deposit;
	}

	public String getEmail() {
		return email;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getName() {
		return name;
	}

	public TaskType getParentType() {
		return parentType;
	}

	public String getPhone() {
		return phone;
	}

	public String getQQ() {
		return QQ;
	}

	public String getSex() {
		return sex;
	}

	public int getStatus() {
		return status;
	}

	public int getSubmitCount() {
		return submitCount;
	}

	public TaskType getSubType() {
		return subType;
	}

	public String getTel() {
		return tel;
	}

	public String getTitle() {
		return title;
	}

	public int getTopType() {
		return topType;
	}

	public User getUser() {
		return user;
	}

	public int getVisit() {
		return visit;
	}

	public void setBounty(float bounty) {
		this.bounty = bounty;
	}

	public void setBountyCount(int bountyCount) {
		this.bountyCount = bountyCount;
	}

	public void setBountyMode(int bountyMode) {
		this.bountyMode = bountyMode;
	}

	public void setBountyPrice(float bountyPrice) {
		this.bountyPrice = bountyPrice;
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

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentType(TaskType parentType) {
		this.parentType = parentType;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}

	public void setSubType(TaskType subType) {
		this.subType = subType;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTopType(int topType) {
		this.topType = topType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public Submit getWin() {
		return win;
	}

	public void setWin(Submit win) {
		this.win = win;
	}
	


}



