package com.xiaba2.task.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_task_user")
public class User extends BaseUUIDEntity {

	/**
	 * 账号
	 */
	@ManyToOne
	private Member member;
	/**
	 * 访问次数
	 */
	@Column
	private int visits;
	
	/**
	 * 粉丝数
	 */
	@Column
	private int followCount;
	
	/**
	 * 头像
	 */
	@Column
	private String head;
	/**
	 * 介绍
	 */
	@Column(length = 2000)
	private String introduce;

	/**
	 * 业务范围
	 */
	@Column
	private String business;

	/**
	 * 积分
	 */
	@Column
	private float score;

	/**
	 * 人民币
	 */
	@Column
	private float money;

	/**
	 * 虚拟货币
	 */
	@Column
	private float virtualMoney;

	/**
	 * 等级
	 */
	@Column
	private int level;

	/**
	 * 用户名
	 */
	@Column
	private String username;

	/**
	 * 支付密码
	 */
	@Column
	private String payPassword;

	/**
	 * 邮箱
	 */
	@Column
	private String email;

	/**
	 * 手机
	 */
	@Column
	private String phone;

	/**
	 * 实名
	 */
	@Column
	private String realname;

	/**
	 * 实名认证
	 */
	@Column
	private int checkRealname;

	/**
	 * 银行认证
	 */
	@Column
	private int checkBank;

	/**
	 * 身份证
	 */
	@Column
	private String licence;

	/**
	 * 身份证图片
	 */
	@Column
	private String licenceImage;

	@Column
	private int checkStatus;

	/**
	 * 密码
	 */
	@Column
	private String password;

	/**
	 * 昵称
	 */
	@Column
	private String nickname;

	/**
	 * 卖家头衔
	 */
	@Column
	private String sellLeveName;

	/**
	 * 买家头衔
	 */
	@Column
	private String buyLevelName;

	@Column
	private String regIp;

	@Column
	private Date regTime;

	/**
	 * 是否实名认证
	 */
	@Column
	private int isCheckPerson;

	/**
	 * 是否企业认证
	 */
	@Column
	private int isCheckCompany;

	@Column
	private String sex;

	@Column
	private String province;

	@Column
	private String city;

	@Column
	private String district;

	@Column
	private String address;

	@Column
	private Date birthday;

	@Column
	private String QQ;
	
	
	/**
	 * 验证串
	 */
	@Column
	private String token;
	
	/**
	 * 过期时间
	 */
	@Column
	private Date expire;
	
	
	
	

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getAddress() {
		return address;
	}

	public String getBusiness() {
		return business;
	}

	public String getBuyLevelName() {
		return buyLevelName;
	}

	public int getCheckBank() {
		return checkBank;
	}

	public int getCheckRealname() {
		return checkRealname;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getEmail() {
		return email;
	}

	public String getHead() {
		return head;
	}

	public String getIntroduce() {
		return introduce;
	}

	public int getIsCheckCompany() {
		return isCheckCompany;
	}

	public int getIsCheckPerson() {
		return isCheckPerson;
	}

	public int getLevel() {
		return level;
	}

	public String getLicence() {
		return licence;
	}

	public String getLicenceImage() {
		return licenceImage;
	}

	public Member getMember() {
		return member;
	}

	public float getMoney() {
		return money;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public String getPhone() {
		return phone;
	}

	public String getProvince() {
		return province;
	}

	public String getRealname() {
		return realname;
	}

	public String getRegIp() {
		return regIp;
	}

	public Date getRegTime() {
		return regTime;
	}

	public float getScore() {
		return score;
	}

	public String getSellLeveName() {
		return sellLeveName;
	}

	public String getSex() {
		return sex;
	}

	public String getUsername() {
		return username;
	}

	public float getVirtualMoney() {
		return virtualMoney;
	}

	public int getVisits() {
		return visits;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public void setBuyLevelName(String buyLevelName) {
		this.buyLevelName = buyLevelName;
	}

	public void setCheckBank(int checkBank) {
		this.checkBank = checkBank;
	}

	public void setCheckRealname(int checkRealname) {
		this.checkRealname = checkRealname;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public void setIsCheckCompany(int isCheckCompany) {
		this.isCheckCompany = isCheckCompany;
	}

	public void setIsCheckPerson(int isCheckPerson) {
		this.isCheckPerson = isCheckPerson;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public void setLicenceImage(String licenceImage) {
		this.licenceImage = licenceImage;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setSellLeveName(String sellLeveName) {
		this.sellLeveName = sellLeveName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVirtualMoney(float virtualMoney) {
		this.virtualMoney = virtualMoney;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

}
