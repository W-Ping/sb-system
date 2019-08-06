package com.ping.po;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author lwp
 * @create 2019/07/13 15:17
 */
@Table(name = "user_info")
public class UserInfoPo extends BasePo {

    /**
     *
     */
    @Column(name = "user_name")
    private String userName;
    /**
     *
     */
    @Column(name = "user_code")
    private String userCode;
    /**
     *
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     *
     */
    @Column(name = "post_type")
    private String postType;
    /**
     *
     */
    private Integer gender;
    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(final Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
