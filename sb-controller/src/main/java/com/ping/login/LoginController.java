package com.ping.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录
 *
 * @author lwp
 * @create 2019/07/13 23:45
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping(value = "/page")
	public String login(Model model) {
		model.addAttribute("uid", "123456789");
		model.addAttribute("name", "Jerry");
		return "login";
	}
	@GetMapping(value = "/register")
	public String register(Model model) {

		return "register";
	}
}
