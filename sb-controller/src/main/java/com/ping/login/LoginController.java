package com.ping.login;

import com.ping.Result;
import com.ping.co.UserInfoCo;
import com.ping.constant.SysConstant;
import com.ping.userinfo.IUserInfoService;
import com.ping.utils.DigestUtil;
import com.ping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String login(Model model, @RequestParam(name = "mobile_phone", required = false) String mobilePhone) {
        model.addAttribute("mobilePhone", mobilePhone);
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }

    /**
     * 注册用户
     *
     * @param userInfoCo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/user_register")
    public Result<String> register(@RequestBody UserInfoCo userInfoCo) {
        boolean b = iUserInfoService.saveUserInfo(userInfoCo);
        return Result.successOrFail(b);
    }

    /**
     * 用户登录
     *
     * @param userInfoCo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/user_login")
    public Result<UserInfoVo> checkLogin(@RequestBody UserInfoCo userInfoCo) {
        UserInfoVo userInfo = iUserInfoService.checkUser(userInfoCo.getMobilePhone(), DigestUtil.md5DoubleEncrypt(userInfoCo.getPassword()), userInfoCo.getPostType());
        return Result.successOrFail(userInfo != null, userInfo);
    }

    /**
     * @param session
     * @return
     */
    @GetMapping(value = "/out_login")
    public String outLogin(HttpSession session) {
        session.removeAttribute(SysConstant.USER_INFO_SESSION_KEY);
        return "redirect:/login/index";
    }

}
