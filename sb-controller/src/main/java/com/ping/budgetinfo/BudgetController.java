package com.ping.budgetinfo;

import com.ping.Result;
import com.ping.co.BudgetInfoCo;
import com.ping.co.SearchCo;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.vo.hosue.BudgetClassifyInfoVo;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseBudgetInfoVo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lwp
 * @create 2019/07/14 21:09
 */
@Controller
@RequestMapping("/budget")
public class BudgetController {
	@Autowired
	private IBudgetInfoService iBudgetInfoService;
	@Autowired
	private IHouseInfoService iHouseInfoService;

	/**
	 * @param houseDetailCode
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/query/list/{houseDetailCode}/{mobilePhone}")
	public String queryBudgetList(Model model, @PathVariable("houseDetailCode") String houseDetailCode,
	                              @PathVariable("mobilePhone") String mobilePhone) {
		BudgetInfoCo budgetInfoCo = new BudgetInfoCo();
		budgetInfoCo.setMobilePhone(mobilePhone);
		HouseDetailInfoVo houseDetailInfo = iHouseInfoService.getHouseDetailInfo(houseDetailCode);
		if (houseDetailInfo != null) {
			List<HouseBudgetInfoVo> houseBudgetInfoVos = houseDetailInfo.getHouseBudgetInfoVos();
			if (!CollectionUtils.isEmpty(houseBudgetInfoVos)) {
				List<String> ignoreBudgetCodes = houseBudgetInfoVos.stream().map(HouseBudgetInfoVo::getBudgetCode).collect(Collectors.toList());
				budgetInfoCo.setIgnoreBudgetCodes(ignoreBudgetCodes);
			}
		}
		List<BudgetInfoVo> budgetInfoVos = iBudgetInfoService.queryBudgetInfoList(budgetInfoCo);
		model.addAttribute("list", budgetInfoVos);
		model.addAttribute("vo", houseDetailInfo);
		return "budget_list";
	}

	@GetMapping(value = "/page/add/{mobile_phone}")
	public String toAddBudget(Model model, @PathVariable("mobile_phone") String mobilePhone) {
		model.addAttribute("mobilePhone", mobilePhone);
		return "budget_add";
	}

	/**
	 * @param houseInfoVo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/save")
	public Result<BudgetInfoVo> saveBudget(@RequestBody BudgetInfoVo houseInfoVo) {
		boolean b = iBudgetInfoService.saveBudgetInfo(houseInfoVo);
		return Result.successOrFail(b, houseInfoVo);
	}

	/**
	 * @param houseInfoVo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/copy/save")
	public Result<BudgetInfoVo> saveCopyBudget(@RequestBody BudgetInfoVo houseInfoVo) {
		boolean b = iBudgetInfoService.saveCopyBudget(houseInfoVo);
		return Result.successOrFail(b, houseInfoVo);
	}

	/**
	 * @param id
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/delete/{id}")
	public Result<Boolean> deleteBudget(@PathVariable("id") Long id) {
		boolean b = iBudgetInfoService.deleteBudgetInfoById(id);
		return Result.successOrFail(b);
	}


	/**
	 * 装修材料-关键字查询
	 *
	 * @param searchCo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/search/keyword")
	public Result<List<BudgetInfoVo>> searchByKeyword(@RequestBody SearchCo searchCo) {
		List<BudgetInfoVo> budgetInfoVos = iBudgetInfoService.searchByKeyword(searchCo);
		return Result.success(budgetInfoVos);
	}

	/**
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/classify/ft/get")
	public Result<List<BudgetClassifyInfoVo>> getFatherClassify() {
		BudgetClassifyInfoVo budgetClassifyInfoVo = new BudgetClassifyInfoVo();
		budgetClassifyInfoVo.setFatherClassifyCode("0");
		List<BudgetClassifyInfoVo> budgetClassifyInfoVos = iBudgetInfoService.getBudgetClassifyInfo(budgetClassifyInfoVo);
		return Result.success(budgetClassifyInfoVos);
	}


	/**
	 * @param fatherClassifyCode
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/classify/sub/{fatherClassifyCode}/get")
	public Result<List<BudgetClassifyInfoVo>> getBudgetClassifyInfo(@PathParam("fatherClassifyCode") String fatherClassifyCode) {
		BudgetClassifyInfoVo budgetClassifyInfoVo = new BudgetClassifyInfoVo();
		budgetClassifyInfoVo.setFatherClassifyCode(fatherClassifyCode);
		List<BudgetClassifyInfoVo> budgetClassifyInfoVos = iBudgetInfoService.getBudgetClassifyInfo(budgetClassifyInfoVo);
		return Result.success(budgetClassifyInfoVos);
	}

	/**
	 * @return
	 */
	@ResponseBody
	@GetMapping("/classify/get")
	public Result<List<Map<String, Object>>> getFullBudgetClassify() {
		List<Map<String, Object>> budgetClassifyInfoListMap = iBudgetInfoService.getBudgetClassifyInfoListMap();
		return Result.success(budgetClassifyInfoListMap);
	}

}
