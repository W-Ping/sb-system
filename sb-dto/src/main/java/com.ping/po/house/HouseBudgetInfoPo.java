package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 房屋装修预算表
 *
 * @author lwp
 */
@Table(name = "house_budget_info")
public class HouseBudgetInfoPo extends BasePo {

	/**
	 *
	 */
	@Column(name = "house_code")
	private String houseCode;
	/**
	 *
	 */
	@Column(name = "house_detail_code")
	private String houseDetailCode;
	/**
	 *
	 */
	@Column(name = "house_budget_code")
	private String houseBudgetCode;
	/**
	 *
	 */
	@Column(name = "budget_code")
	private String budgetCode;
	/**
	 *
	 */
	@Column(name = "budget_name")
	private String budgetName;
	/**
	 *
	 */
	@Column(name = "budget_amount")
	private BigDecimal budgetAmount;
	/**
	 *
	 */
	@Column(name = "budget_count")
	private Integer budgetCount;

	@Transient
	private Integer roomType;
	@Transient
	private String roomNickName;
	@Transient
	private String houseDetailName;

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(final String houseCode) {
		this.houseCode = houseCode;
	}

	public String getHouseDetailCode() {
		return houseDetailCode;
	}

	public void setHouseDetailCode(final String houseDetailCode) {
		this.houseDetailCode = houseDetailCode;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(final String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(final String budgetName) {
		this.budgetName = budgetName;
	}

	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(final BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Integer getBudgetCount() {
		return budgetCount;
	}

	public String getHouseBudgetCode() {
		return houseBudgetCode;
	}

	public void setHouseBudgetCode(final String houseBudgetCode) {
		this.houseBudgetCode = houseBudgetCode;
	}

	public void setBudgetCount(final Integer budgetCount) {
		this.budgetCount = budgetCount;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(final Integer roomType) {
		this.roomType = roomType;
	}

	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(final String roomNickName) {
		this.roomNickName = roomNickName;
	}

	public String getHouseDetailName() {
		return houseDetailName;
	}

	public void setHouseDetailName(final String houseDetailName) {
		this.houseDetailName = houseDetailName;
	}
}
