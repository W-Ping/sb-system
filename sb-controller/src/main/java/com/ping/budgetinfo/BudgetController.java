package com.ping.budgetinfo;

import com.ping.Result;
import com.ping.vo.hosue.BudgetInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwp
 * @create 2019/07/14 21:09
 */
@Controller
@RequestMapping("/budget")
public class BudgetController {
	@Autowired
	private IBudgetInfoService iBudgetInfoService;

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
}
