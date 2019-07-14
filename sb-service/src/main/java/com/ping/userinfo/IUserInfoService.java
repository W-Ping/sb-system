package com.ping.userinfo;

import com.ping.co.UserInfoCo;
import com.ping.po.UserInfoPo;
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
}
