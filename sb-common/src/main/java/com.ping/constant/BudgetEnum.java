package com.ping.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author lwp
 * @create 2019/07/14 18:47
 */
public enum BudgetEnum {
	budget_name(0, "budgetName", "预算名称"),
	max_budget_amount(1, "maxBudgetAmount", "最高金额"),
	min_budget_amount(2, "minBudgetAmount", "最低金额"),
	max_cost(3, "maxCost", "最高成本"),
	min_cost(4, "minCost", "最低成本"),
	actual_cost(5, "actualCost", "实际成本");

	BudgetEnum(Integer type, String key, String name) {
		this.type = type;
		this.key = key;
		this.name = name;
	}

	public static BudgetEnum getBudgetEnum(Integer type) {
		if (type == null) {
			return null;
		}
		Optional<BudgetEnum> first = Arrays.stream(values()).filter(v -> v.getType().equals(type)).findFirst();
		if (first.isPresent()) {
			return first.get();
		}
		return null;
	}

	private Integer type;
	private String key;
	private String name;

	public Integer getType() {
		return type;
	}

	public void setType(final Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}
}
