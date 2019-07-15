package com.ping.login;

import com.ping.Result;
import com.ping.annotation.CacheSave;
import com.ping.co.UserInfoCo;
import com.ping.constant.CacheTypeEnum;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.DigestUtil;
import com.ping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 登录
 *
 * @author lwp
 * @create 2019/07/13 23:45
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IUserInfoService iUserInfoService;

	@GetMapping(value = "/index")
	public String login(Model model) {
		model.addAttribute("uid", "123456789");
		model.addAttribute("name", "Jerry");
		return "login";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}

	@ResponseBody
	@PostMapping(value = "/user_register")
	public Result<String> register(@RequestBody UserInfoCo userInfoCo) {
		try {
			boolean b = iUserInfoService.saveUserInfo(userInfoCo);
			return Result.successOrFial(b, b ? "注册成功" : "注册失败");
		} catch (Exception e) {
			return Result.fail(e.getMessage(), e.getMessage());
		}
	}

	@ResponseBody
	@PostMapping(value = "/user_login")
	public Result<UserInfoVo> checkLogin(@RequestBody UserInfoCo userInfoCo) {
		try {
			UserInfoVo userInfo = iUserInfoService.checkUser(userInfoCo.getMobilePhone(), DigestUtil.md5DoubleEncrypt(userInfoCo.getPassword()));
			if (userInfo != null) {
				return Result.success("登录成功", userInfo);
			}
			return Result.fail("手机或密码不正确", null);
		} catch (Exception e) {
			return Result.fail(e.getMessage(), null);
		}

	}

}
