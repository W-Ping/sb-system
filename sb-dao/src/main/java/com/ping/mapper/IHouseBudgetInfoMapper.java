package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.po.house.HouseBudgetInfoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lwp
 * @create 2019/07/14 10:13
 */
@Repository
public interface IHouseBudgetInfoMapper extends BaseMapper<HouseBudgetInfoPo> {
	/**
	 * @param mobilePhone
	 * @return
	 */
	Map<String, String> calculateBudgetTotalAmount(@Param("mobilePhone") String mobilePhone);

	/**
	 * @param mobilePhone
	 * @return
	 */
	List<HouseBudgetInfoPo> queryBudgetAmountDetailOfMin(@Param("mobilePhone") String mobilePhone);

	/**
	 * @param mobilePhone
	 * @return
	 */
	List<HouseBudgetInfoPo> queryBudgetAmountDetailOfMax(@Param("mobilePhone") String mobilePhone);

	/**
	 * @param mobilePhone
	 * @return
	 */
	List<HouseBudgetInfoPo> queryBudgetAmountDetailOfSelf(@Param("mobilePhone") String mobilePhone);
}
