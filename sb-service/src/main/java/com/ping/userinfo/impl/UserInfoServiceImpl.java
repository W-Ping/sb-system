package com.ping.userinfo.impl;

import com.ping.mapper.IUserInfoMapper;
import com.ping.po.UserInfoPo;
import com.ping.userinfo.IUserInfoService;
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
    public List<UserInfoPo> queryUserInfoList() {
        List<UserInfoPo> userInfoPos = iUserInfoMapper.queryUserInfoList();
        return userInfoPos;
    }
}
