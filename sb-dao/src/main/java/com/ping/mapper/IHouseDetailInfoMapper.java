package com.ping.mapper;

import com.ping.BaseMapper;
import com.ping.co.SearchCo;
import com.ping.po.house.HouseDetailInfoPo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:13
 */
@Repository
public interface IHouseDetailInfoMapper extends BaseMapper<HouseDetailInfoPo> {
    /**
     * @param houseDetailInfoVo
     * @return
     */
    int replaceHouseDetailInfo(HouseDetailInfoVo houseDetailInfoVo);

    /**
     * @param searchCo
     * @return
     */
    List<HouseDetailInfoVo> searchByKeyword(SearchCo searchCo);
}
