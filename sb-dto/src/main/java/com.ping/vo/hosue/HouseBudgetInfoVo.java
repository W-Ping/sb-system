package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

/**
 * 房屋装修预算表
 *
 * @author lwp
 */
public class HouseBudgetInfoVo extends BaseVo {

	/**
	 *
	 */
	private String houseCode;
	/**
	 *
	 */
	private String houseDetailCode;
	/**
	 *
	 */
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
