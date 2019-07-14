package com.ping.userinfo;

import com.ping.Result;
import com.ping.co.UserInfoCo;
import com.ping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author thinkpad
 * @date 2019/7/12 19:24
 * @see
 */
@RestController
public class UserInfoController {
	@Autowired
	private IUserInfoService iUserInfoService;

	@PostMapping(value = "/sb/user/queryUserInfoList")
	public Result<List<UserInfoVo>> queryUserInfoList(@RequestBody UserInfoCo userInfoCo) {
		List<UserInfoVo> userInfoVos = iUserInfoService.queryUserInfoList(userInfoCo);
		return Result.success(userInfoVos);
	}

	@PostMapping(value = "/sb/user/saveUserInfo")
	public Result<Boolean> saveUserInfo(@RequestBody UserInfoCo userInfoCo) {
		boolean b = iUserInfoService.saveUserInfo(userInfoCo);
		return Result.successOrFial(b, null);
	}
}
