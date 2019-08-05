package com.ping.budgetinfo;

import com.ping.co.BudgetInfoCo;
import com.ping.co.SearchCo;
import com.ping.vo.hosue.BudgetClassifyInfoVo;
import com.ping.vo.hosue.BudgetInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author lwp
 * @create 2019/07/14 10:26
 */
public interface IBudgetInfoService {


	/**
	 * @param budgetClassifyInfoVo
	 * @return
	 */
	List<BudgetClassifyInfoVo> getBudgetClassifyInfo(BudgetClassifyInfoVo budgetClassifyInfoVo);

	/**
	 * @return
	 */
	List<Map<String, Object>>  getBudgetClassifyInfoListMap();


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
	 * @param budgetCodes
	 * @return
	 */
	List<BudgetInfoVo> queryBudgetInfosByCodes(List<String> budgetCodes);


	/**
	 * @param houseDetailCode
	 * @return
	 */
	List<BudgetInfoVo> searchByKeyword(SearchCo searchCo);
}
