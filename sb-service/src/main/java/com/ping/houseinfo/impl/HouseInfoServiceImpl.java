package com.ping.houseinfo.impl;

import com.ping.BaseService;
import com.ping.co.HouseInfoCo;
import com.ping.co.UserInfoCo;
import com.ping.constant.SysConstant;
import com.ping.exception.SBRuntimeException;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.mapper.IHouseInfoMapper;
import com.ping.po.house.HouseInfoPo;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.UserInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:39
 */
@Service
public class HouseInfoServiceImpl extends BaseService implements IHouseInfoService {

	@Autowired
	private IHouseInfoMapper iHouseInfoMapper;
	@Autowired
	private IUserInfoService iUserInfoService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveHouseInfo(final HouseInfoVo houseInfoVo) {
		if (houseInfoVo == null) {
			throw new SBRuntimeException("参数不能为空");
		}
		HouseInfoPo houseInfoPo = BeanMapperUtil.map(houseInfoVo, HouseInfoPo.class);
		Example example = new Example(HouseInfoPo.class);
		example.createCriteria().andEqualTo("mobilePhone", houseInfoPo.getMobilePhone())
				.andEqualTo("status", SysConstant.STATUS_0);
		List<HouseInfoPo> houseInfoPos = iHouseInfoMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(houseInfoPos)) {
			houseInfoPo.setId(houseInfoPos.get(0).getId());
			return iHouseInfoMapper.updateByPrimaryKeySelective(houseInfoPo) >= 0;
		} else {
			houseInfoPo.setHouseCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_PRIFIX));
			if (StringUtils.isNotBlank(houseInfoVo.getMobilePhone())) {
				UserInfoCo userInfoCo = new UserInfoCo();
				userInfoCo.setMobilePhone(houseInfoVo.getMobilePhone());
				iUserInfoService.saveUserInfo(userInfoCo);
			}
			return iHouseInfoMapper.insertSelective(houseInfoPo) > 0;
		}
	}

	@Override
	public List<HouseInfoVo> queryHouseInfoList(final HouseInfoCo co) {
		return null;
	}

	@Override
	public HouseInfoVo getHouseInfo(final HouseInfoCo co) {
		Example example = new Example(HouseInfoPo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", SysConstant.STATUS_0);
		if (StringUtils.isNotBlank(co.getHouseCode())) {
			criteria.andEqualTo("houseCode", co.getHouseCode());
		}
		if (co.getHouseType() != null) {
			criteria.andEqualTo("houseType", co.getHouseType());
		}
		if (StringUtils.isNotBlank(co.getMobilePhone())) {
			criteria.andEqualTo("mobilePhone", co.getMobilePhone());
		}
		HouseInfoPo houseInfoPo = iHouseInfoMapper.selectOneByExample(example);
		HouseInfoVo houseInfoVo = BeanMapperUtil.map(houseInfoPo, HouseInfoVo.class);
		return houseInfoVo;
	}

	/**
	 * @param mobilePhone
	 * @return
	 */
	@Override
	public HouseInfoVo getHouseInfo(final String mobilePhone) {
		if (StringUtils.isBlank(mobilePhone)) {
			throw new SBRuntimeException("手机号码不能为空");
		}
		HouseInfoCo houseInfoCo = new HouseInfoCo();
		houseInfoCo.setMobilePhone(mobilePhone);
		return getHouseInfo(houseInfoCo);
	}
}
