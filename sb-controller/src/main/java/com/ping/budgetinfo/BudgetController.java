package com.ping.budgetinfo;

import com.ping.Result;
import com.ping.co.BudgetInfoCo;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseBudgetInfoVo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
	 * @param houseDetailCode
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/query/list/{houseDetailCode}/{mobilePhone}")
	public ModelAndView queryBudgetList(@PathVariable("houseDetailCode") String houseDetailCode,
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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", budgetInfoVos);
		modelAndView.addObject("vo", houseDetailInfo);
		modelAndView.setViewName("budget_list");
		return modelAndView;
	}
}
