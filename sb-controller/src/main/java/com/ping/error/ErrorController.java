package com.ping.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lwp
 * @create 2019/07/24 0:00
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
	@RequestMapping(value = "/400")
	public String error400Page() {
		return "error/400";
	}

	@RequestMapping(value = "/401")
	public String error401Page() {
		return "error/401";
	}

	@RequestMapping(value = "/404")
	public String error404Page(Model model) {
		return "error/404";
	}

	@RequestMapping(value = "/500")
	public String error500Page(Model model) {
		return "error/500";
	}
}
