package com.ping.houseinfo;

import com.ping.Result;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.BudgetInfoCo;
import com.ping.constant.BudgetEnum;
import com.ping.userinfo.IUserInfoService;
import com.ping.vo.UserInfoVo;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseBudgetInfoVo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lwp
 * @create 2019/07/14 11:17
 */
@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private IHouseInfoService iHouseInfoService;


	/**
	 * @param houseInfoVo
	 * @return
	 */
	@PostMapping(value = "/save")
	public Result<HouseInfoVo> submitHouseInfo(@RequestBody HouseInfoVo houseInfoVo) {
		boolean b = iHouseInfoService.saveHouseInfo(houseInfoVo);
		return Result.successOrFail(b);
	}

	@PostMapping(value = "/save_room")
	public Result<HouseDetailInfoVo> submitHouseRoomInfo(@RequestBody HouseDetailInfoVo houseDetailInfoVo) {
		boolean b = iHouseInfoService.saveHouseRoomInfo(houseDetailInfoVo);
		return Result.successOrFail(b, houseDetailInfoVo);
	}


	/**
	 * @param roomType
	 * @param index
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/detail/{roomType}/{index}")
	public Result<HouseDetailInfoVo> getHouseDetail(@PathVariable("roomType") Integer roomType,
	                                                @PathVariable("index") Integer index,
	                                                @RequestParam("mobile_phone") String mobilePhone) {
		HouseDetailInfoVo houseDetailInfo = iHouseInfoService.getHouseDetailInfo(mobilePhone, roomType, index);
		return Result.success(houseDetailInfo);
	}


	/**
	 * @param houseBudgetInfoVo
	 * @return
	 */
	@PostMapping(value = "/detail/decorate/save")
	public Result<HouseBudgetInfoVo> saveHouseBudgetInfo(@RequestBody HouseBudgetInfoVo houseBudgetInfoVo) {
		boolean b = iHouseInfoService.saveHouseBudgetInfo(houseBudgetInfoVo);
		return Result.successOrFail(b, houseBudgetInfoVo);
	}

	/**
	 * @param budgetCode
	 * @return
	 */
	@DeleteMapping(value = "/detail/decorate/delete/{budgetCode}")
	public Result<Boolean> deleteHouseBudgetInfo(@PathVariable("budgetCode") String budgetCode) {
		boolean b = iHouseInfoService.deleteHouseBudgetInfo(budgetCode);
		return Result.successOrFail(b);
	}

	/**
	 * @param houseDetailCode
	 * @param budgetCodes
	 * @return
	 */
	@PostMapping(value = "/detail/decorate/{houseDetailCode}/save")
	public Result<Boolean> saveHouseBudgetInfo(@PathVariable("houseDetailCode") String houseDetailCode,
	                                           @RequestBody List<String> budgetCodes) {
		boolean b = iHouseInfoService.saveHouseBudgetInfo(houseDetailCode, budgetCodes);
		return Result.successOrFail(b);
	}
}
