package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

/**
 * @author lwp
 * @date 2019/8/5 15:01
 * @see
 */
public class BudgetClassifyInfoVo extends BaseVo {
    private String fatherClassifyCode;
    private String fatherClassifyName;
    private String classifyCode;
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

    public String getFatherClassifyName() {
        return fatherClassifyName;
    }

    public void setFatherClassifyName(String fatherClassifyName) {
        this.fatherClassifyName = fatherClassifyName;
    }

    public void setFatherClassifyCode(String fatherClassifyCode) {
        this.fatherClassifyCode = fatherClassifyCode;
    }
}
