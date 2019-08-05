package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author thinkpad
 * @date 2019/8/5 14:59
 * @see
 */
@Table(name = "budget_classify_info")
public class BudgetClassifyInfoPo extends BasePo {
    @Column(name = "father_classify_code")
    private String fatherClassifyCode;
    @Column(name = "classify_code")
    private String classifyCode;
    @Column(name = "classify_name")
    private String classifyName;

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

    public String getFatherClassifyCode() {
        return fatherClassifyCode;
    }

    public void setFatherClassifyCode(String fatherClassifyCode) {
        this.fatherClassifyCode = fatherClassifyCode;
    }
}
