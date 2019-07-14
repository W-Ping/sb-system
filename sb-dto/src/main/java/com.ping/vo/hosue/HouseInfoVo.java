package com.ping.vo.hosue;

import com.ping.vo.BaseVo;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 房屋信息表
 *
 * @author lwp
 */
public class HouseInfoVo extends BaseVo {

	/**
	 *
	 */
	private String houseCode;
	/**
	 * 房屋面积
	 */
	private BigDecimal houseAreaSize;
	/**
	 * 装修合计预算
	 */
	private BigDecimal houseBudgetAmount;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 类型【0：新房，1：旧房子翻新】
	 */
	private Integer houseType;
	/**
	 * 卧室
	 */
	private Integer bedroom;
	/**
	 * 客厅
	 */
	private Integer livingroom;
	/**
	 * 厨房
	 */
	private Integer kitchen;
	/**
	 * 卫生间
	 */
	private Integer toilet;
	/**
	 * 阳台
	 */
	private Integer balcony;

	private String apartment;

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

	public BigDecimal getHouseAreaSize() {
		return houseAreaSize;
	}

	public void setHouseAreaSize(final BigDecimal houseAreaSize) {
		this.houseAreaSize = houseAreaSize;
	}

	public BigDecimal getHouseBudgetAmount() {
		return houseBudgetAmount;
	}

	public void setHouseBudgetAmount(final BigDecimal houseBudgetAmount) {
		this.houseBudgetAmount = houseBudgetAmount;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(final Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getBedroom() {
		return bedroom;
	}

	public void setBedroom(final Integer bedroom) {
		this.bedroom = bedroom;
	}

	public Integer getLivingroom() {
		return livingroom;
	}

	public void setLivingroom(final Integer livingroom) {
		this.livingroom = livingroom;
	}

	public Integer getKitchen() {
		return kitchen;
	}

	public void setKitchen(final Integer kitchen) {
		this.kitchen = kitchen;
	}

	public Integer getToilet() {
		return toilet;
	}

	public void setToilet(final Integer toilet) {
		this.toilet = toilet;
	}

	public Integer getBalcony() {
		return balcony;
	}

	public void setBalcony(final Integer balcony) {
		this.balcony = balcony;
	}

	public String getApartment() {
		if (StringUtils.isBlank(this.apartment)) {
			StringBuilder sb = new StringBuilder();
			sb.append(formatStr(this.bedroom, "室"));
			sb.append(formatStr(this.livingroom, "厅"));
			sb.append(formatStr(this.kitchen, "厨"));
			sb.append(formatStr(this.toilet, "卫"));
			sb.append(formatStr(this.balcony, "阳台"));
			this.apartment = sb.toString();
			if (StringUtils.isNotBlank(this.apartment)) {
				this.apartment = this.apartment.substring(0, this.apartment.length() - 1);
			}
		}
		return this.apartment;
	}

	public void setApartment(final String apartment) {
		this.apartment = apartment;
	}

	private String formatStr(Integer var1, String var2) {
		if (var1 != null) {
			return var1 + var2 + ",";
		}
		return var1 + ",";
	}
}
