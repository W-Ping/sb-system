package com.ping.po;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author lwp
 * @create 2019/07/13 15:17
 */
@Table(name = "user_info")
public class UserInfoPo extends BasePo {

	/**
	 *
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 *
	 */
	@Column(name = "user_code")
	private String userCode;
	/**
	 *
	 */
	@Column(name = "mobile_phone")
	private String mobilePhone;
	/**
	 *
	 */
	private Integer gender;
	/**
	 *
	 */
	private String email;
	/**
	 * 状态【0：有效；1：无效】
	 */
	private Integer status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(final String userCode) {
		this.userCode = userCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(final Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}
}
