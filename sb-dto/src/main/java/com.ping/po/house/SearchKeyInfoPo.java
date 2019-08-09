package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author liu_wp
 * @date 2019/8/9 11:55
 * @see
 */
@Table(name = "search_key_info")
public class SearchKeyInfoPo extends BasePo {

    /**
     *
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;
    /**
     *
     */
    @Column(name = "group_code")
    private String groupCode;
    /**
     *
     */
    private String code;
    /**
     *
     */
    @Column(name = "search_key")
    private String searchKey;
    /**
     *
     */
    @Column(name = "search_name")
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
