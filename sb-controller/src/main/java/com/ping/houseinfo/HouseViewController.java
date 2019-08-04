package com.ping.houseinfo;

import com.ping.Result;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.BudgetInfoCo;
import com.ping.constant.BudgetEnum;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseBudgetInfoVo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lwp
 * @create 2019/08/04 9:11
 */
@Controller
@RequestMapping("/house/page")
public class HouseViewController {
	@Autowired
	private IHouseInfoService iHouseInfoService;
	@Autowired
	private IBudgetInfoService iBudgetInfoService;


	/**
	 * 首页
	 *
	 * @param path
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/{path}")
	public String index(Model model, @PathVariable("path") int path, @RequestParam(name = "mobile_phone") String mobilePhone) {
		HouseInfoVo houseInfo = null;
		Map<String, String> totalAmountMap = null;
		List<BudgetInfoVo> budgetInfoVos = null;
		if (path == 0 || path == 1) {
			houseInfo = iHouseInfoService.getHouseInfoByMobilePhone(mobilePhone);
			if (path == 0) {
				totalAmountMap = iHouseInfoService.calculateBudgetTotalAmount(mobilePhone);
			}
		} else if (path == 2) {
			BudgetInfoCo budgetInfoCo = new BudgetInfoCo();
			budgetInfoCo.setMobilePhone(mobilePhone);
			budgetInfoVos = iBudgetInfoService.queryBudgetInfoList(budgetInfoCo);
		}
		model.addAttribute("type", path);
		model.addAttribute("houseVo", houseInfo);
		model.addAttribute("roomVos", houseInfo != null ? houseInfo.getDetailInfoVos() : new ArrayList<>());
		model.addAttribute("mobilePhone", mobilePhone);
		model.addAttribute("minTotalAmount", totalAmountMap != null ? totalAmountMap.get("min_account_total") : null);
		model.addAttribute("maxTotalAmount", totalAmountMap != null ? totalAmountMap.get("max_account_total") : null);
		model.addAttribute("totalAmount", totalAmountMap != null ? totalAmountMap.get("account_total") : null);
		model.addAttribute("list", budgetInfoVos);
		return "house";
	}

	/**
	 * 房子信息页面
	 *
	 * @param model
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/info/{mobile_phone}")
	public String toHouse(Model model, @PathVariable("mobile_phone") String mobilePhone) {
		HouseInfoVo houseInfo = iHouseInfoService.getHouseInfoByMobilePhone(mobilePhone);
		model.addAttribute("houseVo", houseInfo);
		model.addAttribute("mobilePhone", mobilePhone);
		return "house_info";
	}

	/**
	 * 房子明细页面
	 *
	 * @param model
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/detail/{mobile_phone}")
	public String toHouseDetail(Model model, @PathVariable("mobile_phone") String mobilePhone) {
		HouseInfoVo houseInfo = iHouseInfoService.getHouseInfoByMobilePhone(mobilePhone);
		model.addAttribute("houseVo", houseInfo);
		model.addAttribute("mobilePhone", mobilePhone);
		model.addAttribute("roomVos", houseInfo != null ? houseInfo.getDetailInfoVos() : new ArrayList<>());
		return "house_detail";
	}

	/**
	 * 编辑材料信息页面
	 *
	 * @param model
	 * @param mobilePhone
	 * @param type
	 * @param val
	 * @param budgetCode
	 * @return
	 */
	@GetMapping(value = "/budget/{type}/{mobile_phone}")
	public String toBudget(Model model, @PathVariable("mobile_phone") String mobilePhone,
	                       @PathVariable("type") Integer type,
	                       @RequestParam("val") String val,
	                       @RequestParam("bg_code") String budgetCode) {
		BudgetEnum budgetEnum = BudgetEnum.getBudgetEnum(type);
		model.addAttribute("type", type);
		model.addAttribute("val", val);
		model.addAttribute("name", budgetEnum.getName());
		model.addAttribute("key", budgetEnum.getKey());
		model.addAttribute("budgetCode", budgetCode);
		model.addAttribute("mobilePhone", mobilePhone);
		return "budget_edit";
	}

	/**
	 * 房屋明细装修
	 *
	 * @param houseDetailCode
	 * @param houseBudgetCode
	 * @return
	 */
	@GetMapping(value = "/detail/decorate/{houseDetailCode}")
	public String toHouseDecorate(Model model, @PathVariable("houseDetailCode") String houseDetailCode,
	                              @RequestParam(name = "hbc", required = false) String houseBudgetCode) {
		HouseBudgetInfoVo houseBudgetInfoVo = new HouseBudgetInfoVo();
		houseBudgetInfoVo.setHouseDetailCode(houseDetailCode);
		if (StringUtils.isNotBlank(houseBudgetCode)) {
			houseBudgetInfoVo = iHouseInfoService.getHouseBudgetInfoByCode(houseBudgetCode);
		} else {
			HouseDetailInfoVo houseDetailInfo = iHouseInfoService.getHouseDetailInfo(houseDetailCode);
			if (houseDetailInfo != null) {
				houseBudgetInfoVo.setHouseDetailName(houseDetailInfo.getHouseDetailName());
				houseBudgetInfoVo.setRoomNickName(houseDetailInfo.getRoomNickName());
			}
		}
		model.addAttribute("vo", houseBudgetInfoVo);
		return "house_decorate";
	}

	/**
	 * 预算明细页面
	 *
	 * @param model
	 * @param mobilePhone
	 * @param type
	 * @return
	 */
	@GetMapping(value = "/budgetInfo/detail/{type}/{mobilePhone}")
	public String toHouseBudgetInfo(Model model, @PathVariable("mobilePhone") String mobilePhone,
	                                @PathVariable("type") String type) {
		Map<Integer, List<HouseBudgetInfoVo>> mapList = null;
		List<HouseBudgetInfoVo> houseBudgetInfoVos = iHouseInfoService.queryHouseBudgetInfoList(mobilePhone, type);
		Map<Integer, BigDecimal> totalMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(houseBudgetInfoVos)) {
			mapList = houseBudgetInfoVos.stream().filter(v -> v.getRoomType() != null).collect(Collectors.groupingBy(HouseBudgetInfoVo::getRoomType));
			for (Map.Entry<Integer, List<HouseBudgetInfoVo>> map : mapList.entrySet()) {
				BigDecimal total = map.getValue().stream().map(v -> v.getBudgetAmount().multiply(new BigDecimal(v.getBudgetCount()))).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_HALF_UP);
				totalMap.put(map.getKey(), total);
			}

		}
		model.addAttribute("mobilePhone", mobilePhone);
		model.addAttribute("type", type);
		model.addAttribute("mapList", mapList);
		model.addAttribute("totalMap", totalMap);
		return "house_budget_info";
	}
}
