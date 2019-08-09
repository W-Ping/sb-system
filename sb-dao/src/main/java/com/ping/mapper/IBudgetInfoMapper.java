package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.co.SearchCo;
import com.ping.po.house.BudgetInfoPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:12
 */
@Repository
public interface IBudgetInfoMapper extends BaseMapper<BudgetInfoPo> {

    /**
     * @param budgetInfoCo
     * @return
     */
    List<BudgetInfoPo> searchByKeyword(SearchCo budgetInfoCo);

    /**
     * @param budgetInfoCo
     * @return
     */
    List<BudgetInfoPo> searchByAmount(SearchCo budgetInfoCo);
}
