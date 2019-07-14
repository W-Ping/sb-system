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
	 * @param id
	 * @return
	 */
	boolean deleteBudgetInfoById(Long id);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfoList(BudgetInfoCo budgetInfoVo);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfoListPage(BudgetInfoCo budgetInfoVo);

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	BudgetInfoVo getBudgetInfo(BudgetInfoCo budgetInfoVo);
}
