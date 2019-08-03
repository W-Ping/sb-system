package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.po.house.HouseInfoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author lwp
 * @create 2019/07/14 10:13
 */
@Repository
public interface IHouseInfoMapper extends BaseMapper<HouseInfoPo> {

	/**
	 * @param mobilePhone
	 * @return
	 */
	Map<String, String> calculateBudgetTotalAmount(@Param("mobilePhone") String mobilePhone);
}
