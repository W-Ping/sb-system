package com.ping.co;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:33
 */
public class BudgetInfoCo extends BaseCo {
	private String mobilePhone;
	private List<String> ignoreBudgetCodes;

	public List<String> getIgnoreBudgetCodes() {
		return ignoreBudgetCodes;
	}

	public void setIgnoreBudgetCodes(final List<String> ignoreBudgetCodes) {
		this.ignoreBudgetCodes = ignoreBudgetCodes;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
