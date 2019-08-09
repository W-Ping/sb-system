package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

/**
 * @author liu_wp
 * @date 2019/8/9 11:57
 * @see
 */
public class SearchKeyInfoVo extends BaseVo {

    /**
     *
     */
    private String mobilePhone;
    /**
     *
     */
    private String groupCode;
    /**
     *
     */
    private String code;
    /**
     *
     */
    private String searchKey;
    /**
     *
     */
    private String searchName;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
