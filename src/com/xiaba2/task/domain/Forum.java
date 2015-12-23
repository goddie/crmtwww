package com.xiaba2.task.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_task_forum")
public class Forum extends BaseUUIDEntity {
	/**
	 * 栏目 ID
	 */
	@ManyToOne
	private ForumType type;

	/**
	 * 副栏目 ID
	 */
	@ManyToOne
	private ForumType subType;

	/**
	 * 文档排序
	 */
	@Column
	private int sortRank;

	/**
	 * 是否生成 HTML
	 */
	@Column
	private int genHTML;

	/**
	 * 频道模型
	 */
	@Column
	private int channel;

	/**
	 * 浏览权限
	 */
	@Column
	private int accessRank;

	/**
	 * 点击次数
	 */
	@Column
	private int visits;

	/**
	 * 需要消耗金币
	 */
	@Column
	private int money;

	/**
	 * 文档标题
	 */
	@Column
	private String title;

	/**
	 * 短标题
	 */
	@Column
	private String shortTitle;

	/**
	 * 标题颜色
	 */
	@Column
	private String titleColor;

	/**
	 * 作者
	 */
	@Column
	private String writer;

	/**
	 * 来源
	 */
	@Column
	private String source;

	/**
	 * 缩略图
	 */
	@Column
	private String thumb;

	/**
	 * 作者ID
	 */
	@ManyToOne
	private User user;

	/**
	 * 文档关键词
	 */
	@Column
	private String keywords;

	/**
	 * 最后回复
	 */
	@ManyToOne
	private User lastPost;

	/**
	 * 消耗积分
	 */
	@Column
	private int scores;

	/**
	 * 好评
	 */
	@Column
	private int goodPost;

	/**
	 * 差评
	 */
	@Column
	private int badPost;

	/**
	 * 不允许回复
	 */
	@Column
	private int notPost;

	/**
	 * 描述
	 */
	@Column(length = 1000)
	private String description;

	/**
	 * 自定义文件名
	 */
	@Column
	private String filename;

	/**
	 * 负责审核管理员的 ID
	 */
	@ManyToOne
	private Member check;

	/**
	 * 自定义类别
	 */
	@Column
	private int mType;

	/**
	 * 权重
	 */
	@Column
	private int weight;

	/**
	 * 跳转URL
	 */
	@Column
	private String redirectUrl;

	/**
	 * 用户IP
	 */
	@Column
	private String userIp;

	@Column(length = 2000)
	private String content;

	/**
	 * 是否审核通过
	 */
	@Column
	private int isCheck;

	/**
	 * 最后回复
	 */
	@ManyToOne
	private ForumReply lastReplay;

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public ForumType getType() {
		return type;
	}

	public void setType(ForumType type) {
		this.type = type;
	}

	public ForumReply getLastReplay() {
		return lastReplay;
	}

	public void setLastReplay(ForumReply lastReplay) {
		this.lastReplay = lastReplay;
	}

	public ForumType getSubType() {
		return subType;
	}

	public void setSubType(ForumType subType) {
		this.subType = subType;
	}

	public int getSortRank() {
		return sortRank;
	}

	public void setSortRank(int sortRank) {
		this.sortRank = sortRank;
	}

	public int getGenHTML() {
		return genHTML;
	}

	public void setGenHTML(int genHTML) {
		this.genHTML = genHTML;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getAccessRank() {
		return accessRank;
	}

	public void setAccessRank(int accessRank) {
		this.accessRank = accessRank;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public User getLastPost() {
		return lastPost;
	}

	public void setLastPost(User lastPost) {
		this.lastPost = lastPost;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public int getGoodPost() {
		return goodPost;
	}

	public void setGoodPost(int goodPost) {
		this.goodPost = goodPost;
	}

	public int getBadPost() {
		return badPost;
	}

	public void setBadPost(int badPost) {
		this.badPost = badPost;
	}

	public int getNotPost() {
		return notPost;
	}

	public void setNotPost(int notPost) {
		this.notPost = notPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Member getCheck() {
		return check;
	}

	public void setCheck(Member check) {
		this.check = check;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
