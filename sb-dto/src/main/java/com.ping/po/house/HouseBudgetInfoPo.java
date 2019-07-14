package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;

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
	@Column(name = "budget_code")
	private String budgetCode;

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
}
