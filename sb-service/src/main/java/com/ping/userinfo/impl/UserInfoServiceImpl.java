package com.ping.userinfo.impl;

import com.ping.annotation.CacheSave;
import com.ping.co.UserInfoCo;
import com.ping.constant.CacheKeyRuleEnum;
import com.ping.constant.CacheTypeEnum;
import com.ping.constant.ResultEnum;
import com.ping.constant.SysConstant;
import com.ping.exception.UserException;
import com.ping.exception.ValidateException;
import com.ping.mapper.IUserInfoMapper;
import com.ping.po.UserInfoPo;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.BeanMapperUtil;
import com.ping.utils.DigestUtil;
import com.ping.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        userInfoCo.setPassword(DigestUtil.md5DoubleEncrypt(userInfoCo.getPassword()));
        Example example = new Example(UserInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("mobilePhone", userInfoCo.getMobilePhone());
        UserInfoPo userInfoPo1 = iUserInfoMapper.selectOneByExample(example);
        if (userInfoPo1 != null) {
            throw new UserException(ResultEnum.REQ_PARAMETER_ERROR, "用户信息已存在");
        }
        UserInfoPo userInfoPo = BeanMapperUtil.map(userInfoCo, UserInfoPo.class);
        int count = iUserInfoMapper.insertSelective(userInfoPo);
        return count > 0;
    }

    @Override
    public UserInfoVo getUserInfo(final UserInfoCo userInfoCo) {
        Example example = new Example(UserInfoPo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(userInfoCo.getMobilePhone())) {
            criteria.andEqualTo("mobilePhone", userInfoCo.getMobilePhone());
        }
        if (StringUtils.isNotBlank(userInfoCo.getUserName())) {
            criteria.andLike("userName", "%" + userInfoCo.getUserName() + "%");
        }
        UserInfoPo userInfoPo = iUserInfoMapper.selectOneByExample(example);
        UserInfoVo userInfoVo = BeanMapperUtil.map(userInfoPo, UserInfoVo.class);
        return userInfoVo;
    }

    @Override
    public UserInfoVo getUserInfoByMobilePhone(final String mobilePhone) {
        UserInfoCo userInfoCo = new UserInfoCo();
        userInfoCo.setMobilePhone(mobilePhone);
        return getUserInfo(userInfoCo);
    }

    @Override
    @CacheSave(key = SysConstant.USER_INFO_SESSION_KEY, cacheKeyRule = CacheKeyRuleEnum.NORMAL, cacheType = CacheTypeEnum.SESSION_CACHE)
    public UserInfoVo checkUser(final String mobilePhone, final String password, final String postType) {
        if (StringUtils.isBlank(mobilePhone)) {
            throw new UserException(ResultEnum.REQ_PARAMETER_ERROR, "手机号码不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new UserException(ResultEnum.REQ_PARAMETER_ERROR, "用户密码不能为空");
        }
        if (StringUtils.isBlank(postType)) {
            throw new UserException(ResultEnum.REQ_PARAMETER_ERROR, "用户类型不能为空");
        }
        Example example = new Example(UserInfoPo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SysConstant.STATUS_0);
        criteria.andEqualTo("mobilePhone", mobilePhone);
        criteria.andEqualTo("password", password);
        UserInfoPo userInfoPo = iUserInfoMapper.selectOneByExample(example);
        if (userInfoPo == null) {
            throw new ValidateException(ResultEnum.LOGIN_FAIL);
        }
        String userPostType = userInfoPo.getPostType();
        if (StringUtils.isBlank(userPostType) || userPostType.indexOf(postType) < 0) {
            throw new ValidateException(ResultEnum.LOGIN_FAIL_RIGHT);
        }
        UserInfoVo userInfoVo = BeanMapperUtil.map(userInfoPo, UserInfoVo.class);
        userInfoVo.setLoginPostType(postType);
        return userInfoVo;
    }
}
