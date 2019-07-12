package com.ping.userinfo;

import com.ping.ResponseVo;
import com.ping.po.UserInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author thinkpad
 * @date 2019/7/12 19:24
 * @see
 */
@Controller
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;

    @PostMapping(value = "/user/info/queryUserInfoList")
    @ResponseBody
    public ResponseVo queryUserInfoList(@RequestBody UserInfoPo userInfoPo) {
        List<UserInfoPo> userInfoPos = iUserInfoService.queryUserInfoList();
        return ResponseVo.success(userInfoPos);
    }
}
