package com.ping.houseinfo;

import com.ping.co.HouseInfoCo;
import com.ping.co.SearchCo;
import com.ping.vo.hosue.HouseBudgetInfoVo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import com.ping.vo.hosue.HouseInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author lwp
 * @create 2019/07/14 10:24
 */
public interface IHouseInfoService {
	/**
	 * @param houseInfoVo
	 * @return
	 */
	boolean saveHouseInfo(HouseInfoVo houseInfoVo);

	/**
	 * @param houseDetailInfoVo
	 * @return
	 */
	boolean saveHouseRoomInfo(HouseDetailInfoVo houseDetailInfoVo);

	/**
	 * @param houseBudgetInfoVo
	 * @return
	 */
	boolean saveHouseBudgetInfo(HouseBudgetInfoVo houseBudgetInfoVo);

	/**
	 * @param houseDetailCode
	 * @param budgetCodes
	 * @return
	 */
	boolean saveHouseBudgetInfo(String houseDetailCode, List<String> budgetCodes);

	/**
	 * @param houseBudgetCode
	 * @return
	 */
	boolean deleteHouseBudgetInfo(String houseBudgetCode);

	/**
	 * @param mobilePhone
	 * @param type
	 * @return
	 */
	List<HouseBudgetInfoVo> queryHouseBudgetInfoList(String mobilePhone, String type);

	/**
	 * @param co
	 * @return
	 */
	HouseInfoVo getHouseInfo(HouseInfoCo co);

	/**
	 * @param co
	 * @return
	 */
	HouseInfoVo getHouseInfoDetail(HouseInfoCo co);

	/**
	 * @param mobilePhone
	 * @return
	 */
	HouseInfoVo getHouseInfoByMobilePhone(String mobilePhone);

	/**
	 * 计算装修费用
	 *
	 * @param mobilePhone
	 * @return
	 */
	Map<String, String> calculateBudgetTotalAmount(String mobilePhone);

	/**
	 * @param mobilePhone
	 * @return
	 */
	HouseInfoVo getHouseInfo(String mobilePhone);

	/**
	 * @param mobilePhone
	 * @param roomType
	 * @param roomIndex
	 * @return
	 */
	HouseDetailInfoVo getHouseDetailInfo(String mobilePhone, Integer roomType, Integer roomIndex);

	/**
	 * @param houseDetailCode
	 * @return
	 */
	HouseDetailInfoVo getHouseDetailInfo(String houseDetailCode);

	/**
	 * @param houseBudgetCode
	 * @return
	 */
	HouseBudgetInfoVo getHouseBudgetInfoByCode(String houseBudgetCode);

	/**
	 * @param houseDetailCode
	 * @return
	 */
	List<HouseBudgetInfoVo> queryHouseBudgetInfoByHouseDetailCode(String houseDetailCode);

	/**
	 * @param houseDetailCode
	 * @return
	 */
	List<HouseDetailInfoVo> searchByKeyword(SearchCo searchCo);
}
