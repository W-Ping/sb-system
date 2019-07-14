package com.ping.co;

/**
 * @author lwp
 * @create 2019/07/14 10:37
 */
public class HouseInfoCo extends BaseCo {
	private String houseCode;
	private Integer houseType;
	private String mobilePhone;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(final String houseCode) {
		this.houseCode = houseCode;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(final Integer houseType) {
		this.houseType = houseType;
	}
}
