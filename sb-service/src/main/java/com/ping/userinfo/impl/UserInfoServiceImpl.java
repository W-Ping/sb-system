package com.ping.userinfo.impl;

import com.ping.co.UserInfoCo;
import com.ping.mapper.IUserInfoMapper;
import com.ping.po.UserInfoPo;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thinkpad
 * @date 2019/7/12 19:21
 * @see
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
	@Autowired
	private IUserInfoMapper iUserInfoMapper;

	@Override
	public List<UserInfoVo> queryUserInfoList(UserInfoCo userInfoPo) {
		List<UserInfoPo> userInfoPos = iUserInfoMapper.queryUserInfoList(userInfoPo);
		List<UserInfoVo> userInfoVos = BeanMapperUtil.mapToList(userInfoPos, UserInfoVo.class);
		return userInfoVos;
	}

	@Override
	public boolean saveUserInfo(final UserInfoCo userInfoCo) {
		if (userInfoCo == null) {
			return false;
		}
		UserInfoPo userInfoPo = BeanMapperUtil.map(userInfoCo, UserInfoPo.class);
		int count = iUserInfoMapper.insertSelective(userInfoPo);
		return count > 0;
	}
}
