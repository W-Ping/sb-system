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
	 * 预算金额
	 */
	private BigDecimal budgetAmount;
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
	/**
	 *
	 */
	private String classifyCode;
	/**
	 *
	 */
	private String classifyName;
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

	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(final BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
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

	public String getClassifyCode() {
		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
}
