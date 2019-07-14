package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

import java.math.BigDecimal;

/**
 * 预算表
 *
 * @author lwp
 */
public class BudgetInfoVo extends BaseVo {

	/**
	 *
	 */
	private String budgetName;
	/**
	 *
	 */
	private String budgetCode;
	/**
	 *
	 */
	private String mobilePhone;
	/**
	 * 最小预算金额
	 */
	private BigDecimal minBudgetAmount;
	/**
	 * 最大预算金额
	 */
	private BigDecimal maxBudgetAmount;
	/**
	 * 最小成本
	 */
	private BigDecimal minCost;
	/**
	 * 最大成本
	 */
	private BigDecimal maxCost;
	/**
	 * 实际成本
	 */
	private BigDecimal actualCost;

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(final String budgetName) {
		this.budgetName = budgetName;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(final String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public BigDecimal getMinBudgetAmount() {
		return minBudgetAmount;
	}

	public void setMinBudgetAmount(final BigDecimal minBudgetAmount) {
		this.minBudgetAmount = minBudgetAmount;
	}

	public BigDecimal getMaxBudgetAmount() {
		return maxBudgetAmount;
	}

	public void setMaxBudgetAmount(final BigDecimal maxBudgetAmount) {
		this.maxBudgetAmount = maxBudgetAmount;
	}

	public BigDecimal getMinCost() {
		return minCost;
	}

	public void setMinCost(final BigDecimal minCost) {
		this.minCost = minCost;
	}

	public BigDecimal getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(final BigDecimal maxCost) {
		this.maxCost = maxCost;
	}

	public BigDecimal getActualCost() {
		return actualCost;
	}

	public void setActualCost(final BigDecimal actualCost) {
		this.actualCost = actualCost;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
