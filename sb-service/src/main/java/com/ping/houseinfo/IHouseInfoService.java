package com.ping.houseinfo;

import com.ping.co.HouseInfoCo;
import com.ping.vo.hosue.HouseDetailInfoVo;
import com.ping.vo.hosue.HouseInfoVo;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:24
 */
public interface IHouseInfoService {
    /**
     * @param houseInfoVo
     * @return
     */
    boolean saveHouseInfo(HouseInfoVo houseInfoVo);

    /**
     * @param houseDetailInfoVo
     * @return
     */
    boolean saveHouseRoomInfo(HouseDetailInfoVo houseDetailInfoVo);

    /**
     * @param co
     * @return
     */
    List<HouseInfoVo> queryHouseInfoList(HouseInfoCo co);

    /**
     * @param co
     * @return
     */
    HouseInfoVo getHouseInfo(HouseInfoCo co);

    /**
     * @param co
     * @return
     */
    HouseInfoVo getHouseInfoDetail(HouseInfoCo co);

    /**
     * @param mobilePhone
     * @return
     */
    HouseInfoVo getHouseInfoByMobilePhone(String mobilePhone);

    /**
     * @param mobilePhone
     * @return
     */
    HouseInfoVo getHouseInfo(String mobilePhone);
}
