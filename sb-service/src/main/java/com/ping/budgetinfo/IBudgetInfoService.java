package com.ping.budgetinfo;

import com.ping.co.BudgetInfoCo;
import com.ping.vo.hosue.BudgetInfoVo;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:26
 */
public interface IBudgetInfoService {

	/**
	 * @param budgetInfoVos
	 * @return
	 */
	boolean saveBudgetInfoBatch(List<BudgetInfoVo> budgetInfoVos);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	boolean saveBudgetInfo(BudgetInfoVo budgetInfoVo);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	boolean saveCopyBudget(BudgetInfoVo budgetInfoVo);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteBudgetInfoById(Long id);

	/**
	 * @param budgetInfoCo
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfoList(BudgetInfoCo budgetInfoCo);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfoListPage(BudgetInfoCo budgetInfoVo);


	/**
	 * @param budgetCodes
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfosByCodes(List<String> budgetCodes);
}
