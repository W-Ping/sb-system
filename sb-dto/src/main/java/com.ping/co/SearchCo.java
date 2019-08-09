package com.ping.co;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lwp
 * @date 2019/8/5 11:23
 * @see
 */
public class SearchCo {
    private String keyword;
    private String classifyCode;
    private String mobilePhone;
    private BigDecimal maxAmount;
    private BigDecimal minAmount;
    private String searchGroupCode;

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getSearchGroupCode() {
        return searchGroupCode;
    }

    public void setSearchGroupCode(String searchGroupCode) {
        this.searchGroupCode = searchGroupCode;
    }
}
