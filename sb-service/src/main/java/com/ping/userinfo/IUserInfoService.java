package com.ping.userinfo;

import com.ping.co.UserInfoCo;
import com.ping.vo.UserInfoVo;

import java.util.List;

/**
 * @author liu_wp
 * @date Created in 2019/7/12 19:19
 * @see
 */
public interface IUserInfoService {
    /**
     * @return
     */
    List<UserInfoVo> queryUserInfoList(UserInfoCo userInfoPo);

    /**
     * @param userInfoCo
     * @return
     */
    boolean saveUserInfo(UserInfoCo userInfoCo);

    /**
     * @param userInfoCo
     * @return
     */
    UserInfoVo getUserInfo(UserInfoCo userInfoCo);

    /**
     * @param mobilePhone
     * @return
     */
    UserInfoVo getUserInfoByMobilePhone(String mobilePhone);

    /**
     * @param mobilePhone
     * @param password
     * @return
     */
    UserInfoVo checkUser(String mobilePhone, String password, String postType);


}
