package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.po.house.HouseDetailInfoPo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import org.springframework.stereotype.Repository;

/**
 * @author lwp
 * @create 2019/07/14 10:13
 */
@Repository
public interface IHouseDetailInfoMapper extends BaseMapper<HouseDetailInfoPo> {
	/**
	 * @param houseDetailInfoVo
	 * @return
	 */
	int replaceHouseDetailInfo(HouseDetailInfoVo houseDetailInfoVo);
}
