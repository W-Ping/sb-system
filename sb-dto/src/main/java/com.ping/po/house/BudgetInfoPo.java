package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 预算表
 *
 * @author lwp
 */
@Table(name = "budget_info")
public class BudgetInfoPo extends BasePo {

	/**
	 *
	 */
	@Column(name = "budget_name")
	private String budgetName;
	/**
	 *
	 */
	@Column(name = "budget_code")
	private String budgetCode;
	/**
	 *
	 */
	@Column(name = "mobile_phone")
	private String mobilePhone;
	/**
	 * 预算金额
	 */
	@Column(name = "budget_amount")
	private BigDecimal budgetAmount;
	/**
	 * 最小成本
	 */
	@Column(name = "min_cost")
	private BigDecimal minCost;
	/**
	 * 最大成本
	 */
	@Column(name = "max_cost")
	private BigDecimal maxCost;
	/**
	 * 实际成本
	 */
	@Column(name = "actual_cost")
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
}
