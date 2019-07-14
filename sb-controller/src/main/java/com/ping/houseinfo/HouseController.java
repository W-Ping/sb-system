package com.ping.houseinfo;

import com.ping.Result;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.BudgetInfoCo;
import com.ping.co.HouseInfoCo;
import com.ping.constant.BudgetEnum;
import com.ping.constant.SysConstant;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

	/**
	 * @param path
	 * @param mobilePhone
	 * @return
	 */
	@GetMapping(value = "/{path}")
	public ModelAndView index(@PathVariable("path") String path, @RequestParam("mobile_phone") String mobilePhone) {
		ModelAndView modelAndView = new ModelAndView();
		if (SysConstant.PATH_BUDGET.equals(path)) {
			if (StringUtils.isNotBlank(mobilePhone)) {
				BudgetInfoCo budgetInfoCo = new BudgetInfoCo();
				budgetInfoCo.setMobilePhone(mobilePhone);
				List<BudgetInfoVo> budgetInfoVos = iBudgetInfoService.queryBudgetInfoList(budgetInfoCo);
				modelAndView.addObject("list", budgetInfoVos);
			}
			modelAndView.addObject("type", 1);
		} else if (SysConstant.PATH_HOUSE.equals(path)) {
			if (StringUtils.isNotBlank(mobilePhone)) {
				HouseInfoCo houseInfoCo = new HouseInfoCo();
				houseInfoCo.setMobilePhone(mobilePhone);
				HouseInfoVo houseInfo = iHouseInfoService.getHouseInfo(houseInfoCo);
				modelAndView.addObject("vo", houseInfo);
			}
			modelAndView.addObject("type", 0);
		}
		modelAndView.addObject("mobilePhone", mobilePhone);
		modelAndView.setViewName("house");
		return modelAndView;
	}

	/**
	 * @param mobilePhone
	 * @param type
	 * @param budgetCode
	 * @return
	 */
	@GetMapping(value = "/budget/{type}/{mobile_phone}")
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
		return Result.successOrFial(b, houseInfoVo);
	}


}
