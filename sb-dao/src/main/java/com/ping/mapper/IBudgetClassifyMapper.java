package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.po.house.BudgetClassifyInfoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liu_wp
 * @date Created in 2019/8/5 15:03
 * @see
 */
@Repository
public interface IBudgetClassifyMapper extends BaseMapper<BudgetClassifyInfoPo> {

    /**
     * @param classifyName
     * @return
     */
    BudgetClassifyInfoPo getFatherClassifyInfoByName(@Param("classifyName") String classifyName);
}
