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
@Controller
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private IHouseInfoService iHouseInfoService;
	@Autowired
	private IBudgetInfoService iBudgetInfoService;
	@Autowired
	private IUserInfoService iUserInfoService;

	/**
	 * @param path
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/page/{path}")
	public ModelAndView index(@PathVariable("path") int path, @RequestParam(required = false, name = "mobile_phone") String mobilePhone) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isMobilePhone = StringUtils.isNotBlank(mobilePhone);
		boolean isRegister = false;
		boolean permitDecoration = false;
		HouseInfoVo houseInfo = null;
		Map<String, String> totalAmountMap = null;
		if (isMobilePhone) {
			UserInfoVo userInfoVo = iUserInfoService.getUserInfoByMobilePhone(mobilePhone);
			if (userInfoVo != null) {
				isRegister = true;
			}
		}
		if (path == 0 || path == 1 || path == 2) {
			if (isMobilePhone) {
				houseInfo = iHouseInfoService.getHouseInfoByMobilePhone(mobilePhone);
				if (path == 0) {
					totalAmountMap = iHouseInfoService.calculateBudgetTotalAmount(mobilePhone);
//					modelAndView.addObject("minTotalAmount", totalAmountMap != null ? totalAmountMap.get("min_account_total") : null);
//					modelAndView.addObject("maxTotalAmount", totalAmountMap != null ? totalAmountMap.get("max_account_total") : null);
//					modelAndView.addObject("totalAmount", totalAmountMap != null ? totalAmountMap.get("account_total") : null);
				}
			}
			if (houseInfo != null) {
				permitDecoration = true;
			}
		} else if (path == 3) {
			if (isMobilePhone) {
				BudgetInfoCo budgetInfoCo = new BudgetInfoCo();
				budgetInfoCo.setMobilePhone(mobilePhone);
				List<BudgetInfoVo> budgetInfoVos = iBudgetInfoService.queryBudgetInfoList(budgetInfoCo);
				modelAndView.addObject("list", budgetInfoVos);
			}
		}
		modelAndView.addObject("houseVo", houseInfo);
		modelAndView.addObject("roomVos", houseInfo != null ? houseInfo.getDetailInfoVos() : new ArrayList<>());
		modelAndView.addObject("type", path);
		modelAndView.addObject("permitDecoration", permitDecoration);
		modelAndView.addObject("isRegister", isRegister);
		modelAndView.addObject("mobilePhone", mobilePhone);
		modelAndView.addObject("minTotalAmount", totalAmountMap != null ? totalAmountMap.get("min_account_total") : null);
		modelAndView.addObject("maxTotalAmount", totalAmountMap != null ? totalAmountMap.get("max_account_total") : null);
		modelAndView.addObject("totalAmount", totalAmountMap != null ? totalAmountMap.get("account_total") : null);
		modelAndView.setViewName("house");
		return modelAndView;
	}

	/**
	 * @param mobilePhone
	 * @param type
	 * @param budgetCode
	 * @return
	 */
	@GetMapping(value = "/page/budget/{type}/{mobile_phone}")
	public ModelAndView toBudget(@PathVariable("mobile_phone") String mobilePhone,
	                             @PathVariable("type") Integer type,
	                             @RequestParam("val") String val,
	                             @RequestParam("bg_code") String budgetCode) {
		BudgetEnum budgetEnum = BudgetEnum.getBudgetEnum(type);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("budget_edit");
		modelAndView.addObject("type", type);
		modelAndView.addObject("val", val);
		modelAndView.addObject("name", budgetEnum.getName());
		modelAndView.addObject("key", budgetEnum.getKey());
		modelAndView.addObject("budgetCode", budgetCode);
		modelAndView.addObject("mobilePhone", mobilePhone);
		return modelAndView;
	}

	/**
	 * @param houseInfoVo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/save")
	public Result<HouseInfoVo> submitHouseInfo(@RequestBody HouseInfoVo houseInfoVo) {
		boolean b = iHouseInfoService.saveHouseInfo(houseInfoVo);
		return Result.successOrFail(b);
	}

	@ResponseBody
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
	@ResponseBody
	@GetMapping(value = "/detail/{roomType}/{index}")
	public Result<HouseDetailInfoVo> getHouseDetail(@PathVariable("roomType") Integer roomType,
	                                                @PathVariable("index") Integer index,
	                                                @RequestParam("mobile_phone") String mobilePhone) {
		HouseDetailInfoVo houseDetailInfo = iHouseInfoService.getHouseDetailInfo(mobilePhone, roomType, index);
		return Result.success(houseDetailInfo);
	}

	/**
	 * @param houseDetailCode
	 * @param houseBudgetCode
	 * @return
	 */
	@GetMapping(value = "/detail/decorate/{houseDetailCode}")
	public ModelAndView toHouseDecorate(@PathVariable("houseDetailCode") String houseDetailCode,
	                                    @RequestParam(name = "hbc", required = false) String houseBudgetCode) {
		HouseBudgetInfoVo houseBudgetInfoVo = new HouseBudgetInfoVo();

		houseBudgetInfoVo.setHouseDetailCode(houseDetailCode);
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtils.isNotBlank(houseBudgetCode)) {
			houseBudgetInfoVo = iHouseInfoService.getHouseBudgetInfoByCode(houseBudgetCode);
		} else {
			HouseDetailInfoVo houseDetailInfo = iHouseInfoService.getHouseDetailInfo(houseDetailCode);
			if (houseDetailInfo != null) {
				houseBudgetInfoVo.setHouseDetailName(houseDetailInfo.getHouseDetailName());
				houseBudgetInfoVo.setRoomNickName(houseDetailInfo.getRoomNickName());
			}
		}
		modelAndView.addObject("vo", houseBudgetInfoVo);
		modelAndView.setViewName("house_decorate");
		return modelAndView;
	}

	/**
	 * @param houseBudgetInfoVo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/detail/decorate/save")
	public Result<HouseBudgetInfoVo> saveHouseBudgetInfo(@RequestBody HouseBudgetInfoVo houseBudgetInfoVo) {
		boolean b = iHouseInfoService.saveHouseBudgetInfo(houseBudgetInfoVo);
		return Result.successOrFail(b, houseBudgetInfoVo);
	}

	/**
	 * @param budgetCode
	 * @return
	 */
	@ResponseBody
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
	@ResponseBody
	@PostMapping(value = "/detail/decorate/{houseDetailCode}/save")
	public Result<Boolean> saveHouseBudgetInfo(@PathVariable("houseDetailCode") String houseDetailCode,
	                                           @RequestBody List<String> budgetCodes) {
		boolean b = iHouseInfoService.saveHouseBudgetInfo(houseDetailCode, budgetCodes);
		return Result.successOrFail(b);
	}
}
