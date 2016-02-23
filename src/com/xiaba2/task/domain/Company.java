package com.xiaba2.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_task_company")
public class Company extends BaseUUIDEntity {

	
	/**
	 * 公司名称
	 */
	@Column
	private String name;
	/**
	 * 经营范围
	 */
	@Column
	private String service;
	/**
	 * 省
	 */
	@Column
	private String province;
	/**
	 * 市
	 */
	@Column
	private String city;
	/**
	 * 区
	 */
	@Column
	private String district;
	/**
	 * 营业执照
	 */
	@Column
	private String licence;
	/**
	 * 营业执照扫描件
	 */
	@Column
	private String licenceImage;
	/**
	 * 法人代表
	 */
	@Column
	private String person;
	/**
	 * 法人代表身份证
	 */
	@Column
	private String personLicence;
	/**
	 * 公司电话
	 */
	@Column
	private String phone;
	
	@ManyToOne
	private User user;
	
	/**
	 * 认证状态
	 */
	@Column
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

 

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

 

	public String getLicenceImage() {
		return licenceImage;
	}

	public void setLicenceImage(String licenceImage) {
		this.licenceImage = licenceImage;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPersonLicence() {
		return personLicence;
	}

	public void setPersonLicence(String personLicence) {
		this.personLicence = personLicence;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
