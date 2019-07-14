package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 房屋信息表
 *
 * @author lwp
 */
@Table(name = "house_info")
public class HouseInfoPo extends BasePo {

	/**
	 *
	 */
	@Column(name = "house_code")
	private String houseCode;
	/**
	 * 房屋面积
	 */
	@Column(name = "house_area_size")
	private BigDecimal houseAreaSize;
	/**
	 * 装修合计预算
	 */
	@Column(name = "house_budget_amount")
	private BigDecimal houseBudgetAmount;
	/**
	 * 手机号码
	 */
	@Column(name = "mobile_phone")
	private String mobilePhone;
	/**
	 * 类型【0：新房，1：旧房子翻新】
	 */
	@Column(name = "house_type")
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
