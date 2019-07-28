package com.ping.houseinfo.impl;

import com.ping.BaseService;
import com.ping.co.HouseInfoCo;
import com.ping.constant.ResultEnum;
import com.ping.constant.SysConstant;
import com.ping.exception.ValidateException;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.mapper.IHouseDetailInfoMapper;
import com.ping.mapper.IHouseInfoMapper;
import com.ping.po.house.HouseDetailInfoPo;
import com.ping.po.house.HouseInfoPo;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.hosue.HouseDetailInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	private IHouseDetailInfoMapper iHouseDetailInfoMapper;

	@Override
	public boolean saveHouseInfo(final HouseInfoVo houseInfoVo) {
		if (houseInfoVo == null) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR);
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

	@Override
	public HouseInfoVo getHouseInfoDetail(HouseInfoCo co) {
		HouseInfoVo houseInfoVo = getHouseInfo(co);
		if (houseInfoVo != null) {
			Example example = new Example(HouseDetailInfoPo.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("status", SysConstant.STATUS_0);
			criteria.andEqualTo("houseCode", houseInfoVo.getHouseCode());
			List<HouseDetailInfoPo> houseDetailInfoPos = iHouseDetailInfoMapper.selectByExample(example);
			List<HouseDetailInfoVo> houseDetailInfoVos = BeanMapperUtil.mapToList(houseDetailInfoPos, HouseDetailInfoVo.class);
			houseInfoVo.setDetailInfoVos(houseDetailInfoVos);
		}
		return houseInfoVo;
	}

	/**
	 * @param mobilePhone
	 * @return
	 */
	@Override
	public HouseInfoVo getHouseInfoByMobilePhone(final String mobilePhone) {
		HouseInfoCo houseInfoCo = new HouseInfoCo();
		houseInfoCo.setMobilePhone(mobilePhone);
		return getHouseInfoDetail(houseInfoCo);
	}

	/**
	 * @param mobilePhone
	 * @return
	 */
	@Override
	public HouseInfoVo getHouseInfo(final String mobilePhone) {
		if (StringUtils.isBlank(mobilePhone)) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "手机号码不能为空");
		}
		HouseInfoCo houseInfoCo = new HouseInfoCo();
		houseInfoCo.setMobilePhone(mobilePhone);
		return getHouseInfo(houseInfoCo);
	}

	/**
	 * @param houseDetailInfoVo
	 * @return
	 */
	@Override
	public boolean saveHouseRoomInfo(HouseDetailInfoVo houseDetailInfoVo) {
		checkRoomInfo(houseDetailInfoVo);
		HouseInfoPo houseInfoPo = new HouseInfoPo();
		houseInfoPo.setHouseCode(houseDetailInfoVo.getHouseCode());
		houseInfoPo.setStatus(SysConstant.STATUS_0);
		houseInfoPo = iHouseInfoMapper.selectOne(houseInfoPo);
		if (houseInfoPo == null) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间主档信息未填写");
		}
		checkRoomTypeInfo(houseDetailInfoVo, houseInfoPo);
		HouseDetailInfoPo houseDetailInfoPo = BeanMapperUtil.map(houseDetailInfoVo, HouseDetailInfoPo.class);
		houseDetailInfoPo.setHouseDetailCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_DETAIL_PRIFIX));
		int count = iHouseDetailInfoMapper.insertSelective(houseDetailInfoPo);
		return count > 0;
	}

	private void checkRoomInfo(HouseDetailInfoVo houseDetailInfoVo) {
		if (houseDetailInfoVo == null) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR);
		}
		if (StringUtils.isBlank(houseDetailInfoVo.getHouseCode())) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间编码不能为空");
		}
		if (houseDetailInfoVo.getRoomType() == null) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间类型不能为空");
		}
	}

	private void checkRoomTypeInfo(HouseDetailInfoVo houseDetailInfoVo, HouseInfoPo houseInfoPo) {
		Example example = new Example(HouseDetailInfoPo.class);
		example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
				.andEqualTo("roomType", houseDetailInfoVo.getRoomType());
		int roomCount = iHouseDetailInfoMapper.selectCountByExample(example);
		boolean flag = false;
		String error = null;
		switch (houseDetailInfoVo.getRoomType()) {
			case 0:
				flag = houseInfoPo.getBedroom() == null || houseInfoPo.getBedroom() < (roomCount + 1);
				error = flag ? "房间卧室信息不可添加" : null;
				break;
			case 1:
				flag = houseInfoPo.getLivingroom() == null || houseInfoPo.getLivingroom() < (roomCount + 1);
				error = flag ? "房间客厅信息不可添加" : null;
				break;
			case 2:
				flag = houseInfoPo.getKitchen() == null || houseInfoPo.getKitchen() < (roomCount + 1);
				error = flag ? "房间厨房信息不可添加" : null;
				break;
			case 3:
				flag = houseInfoPo.getToilet() == null || houseInfoPo.getToilet() < (roomCount + 1);
				error = flag ? "房间卫生间信息不可添加" : null;
				break;
			case 4:
				flag = houseInfoPo.getBalcony() == null || houseInfoPo.getBalcony() < (roomCount + 1);
				error = flag ? "房间阳台信息不可添加" : null;
				break;
			default:
				break;
		}
		if (flag) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, error);
		}
	}
}
