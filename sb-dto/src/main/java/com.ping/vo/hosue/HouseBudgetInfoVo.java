package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

import java.math.BigDecimal;

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
    private String houseDetailName;
    /**
     *
     */
    private String roomNickName;
    /**
     *
     */
    private Integer roomType;
    /**
     *
     */
    private String houseBudgetCode;
    /**
     *
     */
    private String budgetCode;
    /**
     *
     */
    private String budgetName;
    /**
     *
     */
    private BigDecimal budgetAmount;
    /**
     *
     */
    private Integer budgetCount;

    /**
     *
     */
    private String classifyCode;
    /**
     *
     */
    private String classifyName;
    /**
     *
     */
    private String fatherClassifyName;


    public String getFatherClassifyName() {
        return fatherClassifyName;
    }

    public void setFatherClassifyName(String fatherClassifyName) {
        this.fatherClassifyName = fatherClassifyName;
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

    public String getHouseDetailName() {
        return houseDetailName;
    }

    public void setHouseDetailName(final String houseDetailName) {
        this.houseDetailName = houseDetailName;
    }

    public String getRoomNickName() {
        return roomNickName;
    }

    public void setRoomNickName(final String roomNickName) {
        this.roomNickName = roomNickName;
    }

    public Integer getBudgetCount() {
        return budgetCount;
    }

    public void setBudgetCount(final Integer budgetCount) {
        this.budgetCount = budgetCount;
    }

    public String getHouseBudgetCode() {
        return houseBudgetCode;
    }

    public void setHouseBudgetCode(final String houseBudgetCode) {
        this.houseBudgetCode = houseBudgetCode;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(final Integer roomType) {
        this.roomType = roomType;
    }
}
