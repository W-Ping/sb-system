package com.ping.mapper;

import com.ping.po.UserInfoPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liu_wp
 * @date Created in 2019/7/12 19:03
 * @see
 */
@Repository
public interface IUserInfoMapper {

    /**
     * @return
     */
    List<UserInfoPo> queryUserInfoList();
}
