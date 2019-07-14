package com.ping.vo;

/**
 * @author lwp
 */
public class UserInfoVo extends BaseVo {
	/**
	 *
	 */
	private String userName;
	/**
	 *
	 */
	private String userCode;
	/**
	 *
	 */
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
