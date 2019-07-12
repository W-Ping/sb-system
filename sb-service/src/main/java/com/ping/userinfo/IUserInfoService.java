package com.ping.userinfo;

import com.ping.po.UserInfoPo;

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
    List<UserInfoPo> queryUserInfoList();
}
