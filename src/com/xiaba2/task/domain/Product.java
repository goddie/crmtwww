package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 发布商品
 * 
 * @author goddie
 *
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_task_product")
public class Product extends BaseUUIDEntity {

	/**
	 * 商家
	 */
	@ManyToOne
	private User user;

	/**
	 * 商品名称
	 */
	@Column
	private String name;

	/**
	 * 服务分类
	 */
	@ManyToOne
	private TaskType parentType;

	/**
	 * 服务分类
	 */
	@ManyToOne
	private TaskType subType;
	/**
	 * 商品分类1商品 2服务
	 */
	@Column
	private int topType;

	/**
	 * 图片
	 */
	@Column(length = 2000)
	private String album;

	/**
	 * 售价
	 */
	@Column
	private float price;

	/**
	 * 商品描述
	 */
	@Column(length = 2000)
	private String info;

	/**
	 * 状态
	 */
	@Column
	private int status;

	/**
	 * 标签
	 */
	@Column
	private String tag;

	/**
	 * 缩略图
	 */
	@Column
	private String thumb;
	
	/**
	 * 成交次数
	 */
	@Column
	private int tradeCount;
	
	/**
	 * 访问次数
	 */
	@Column
	private int visitCount;
	
	/**
	 * 收藏人数
	 */
	@Column
	private int collectCount;
	
	/**
	 * 商品
	 */
	@Column
	private String attachment;
	
	/**
	 * 手机
	 */
	@Column
	private String phone;
	
	/**
	 * QQ
	 */
	@Column
	private String QQ;
	
	/**
	 * 原创证明
	 */
	@Column
	private String proof;
	
	/**
	 * 银行帐号
	 */
	@Column
	private String bankNo;
	
	/**
	 * 银行户名
	 */
	@Column
	private String bankAccount;
	
	/**
	 * 银行名
	 */
	@Column
	private String bankName;
	
	/**
	 * 是否审核通过
	 */
	@Column
	private int isCheck;

	
	/**
	 * 是否上架
	 */
	@Column
	private int isOnSale;
	
	
	

	public int getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(int isOnSale) {
		this.isOnSale = isOnSale;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getProof() {
		return proof;
	}

	public void setProof(String proof) {
		this.proof = proof;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getAlbum() {
		return album;
	}

	public String getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public TaskType getParentType() {
		return parentType;
	}

	public float getPrice() {
		return price;
	}

	public int getStatus() {
		return status;
	}

	public TaskType getSubType() {
		return subType;
	}

	public String getTag() {
		return tag;
	}

	public int getTopType() {
		return topType;
	}

	public User getUser() {
		return user;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentType(TaskType parentType) {
		this.parentType = parentType;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSubType(TaskType subType) {
		this.subType = subType;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTopType(int topType) {
		this.topType = topType;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
