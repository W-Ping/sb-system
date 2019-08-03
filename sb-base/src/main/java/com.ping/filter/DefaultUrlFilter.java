package com.ping.filter;

import com.ping.config.GlobalExceptionHandler;
import com.ping.constant.SysConstant;
import com.ping.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author lwp
 * @create 2019/07/15 12:59
 */
public class DefaultUrlFilter implements Filter {
	private final Logger logger = LogManager.getLogger(DefaultUrlFilter.class);
	private static final String EMP_TOKEN = "";
	private static final String LOGIN_URL_KEY = "login-url";
	private static final String IGNORE_FILTER_URL_KEY = "ignore-filter-url";
	private static final String IGNORE_FILTER_URL_SPILT = ",";

	private String loginUrl;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String requestURI = httpServletRequest.getRequestURI();
		if (isAjax(httpServletRequest) || checkUrl(requestURI) || checkLogin(httpServletRequest)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			logger.warn("{}，重定向登录页面", requestURI);
			httpServletResponse.sendRedirect(loginUrl);
//			httpServletRequest.getRequestDispatcher(loginUrl).forward(servletRequest, servletResponse);
		}

	}

	/**
	 * @param requestUrl
	 * @return
	 */
	private boolean checkUrl(String requestUrl) {
		loginUrl = PropertiesUtil.getProperty(LOGIN_URL_KEY);
		if (requestUrl.equals(loginUrl)) {
			return true;
		}
		String ignoreFilterUrl = PropertiesUtil.getProperty(IGNORE_FILTER_URL_KEY);
		if (StringUtils.isBlank(ignoreFilterUrl)) {
			return true;
		} else {
			//配置多个路径：/static/,/public/
			if (ignoreFilterUrl.indexOf(IGNORE_FILTER_URL_SPILT) > 0) {
				String[] urlList = ignoreFilterUrl.split(",");
				if (urlList != null && urlList.length > 0) {
					return Arrays.stream(urlList).filter(v -> StringUtils.isNotBlank(v)).anyMatch(v -> checkRequestUrl(requestUrl, v));
				}
			} else {
				return checkRequestUrl(requestUrl, ignoreFilterUrl);
			}
		}
		return false;
	}

	/**
	 * @param requestUrl
	 * @param ignoreUrl
	 * @return
	 */
	private boolean checkRequestUrl(String requestUrl, String ignoreUrl) {
		if (ignoreUrl.lastIndexOf("*") >= 0) {
			String urlPrefix = ignoreUrl.substring(0, ignoreUrl.lastIndexOf("*"));
			return requestUrl.startsWith(urlPrefix)
					&& requestUrl.length() > urlPrefix.length();
		} else if (ignoreUrl.lastIndexOf("/") >= 0) {
			return requestUrl.startsWith(ignoreUrl)
					&& requestUrl.length() > ignoreUrl.length();
		} else {
			return requestUrl.startsWith(ignoreUrl);
		}
	}

	private boolean checkLogin(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader(EMP_TOKEN);
		Object userInfo = httpServletRequest.getSession().getAttribute(SysConstant.USER_INFO_SESSION_KEY);
		if (userInfo != null) {
			return true;
		}
		logger.info("用户未登录");
		return false;
	}

	@Override
	public void destroy() {

	}

	public boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null &&
				"XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
}
