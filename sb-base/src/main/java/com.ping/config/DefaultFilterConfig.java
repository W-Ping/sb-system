package com.ping.config;

import com.ping.filter.DefaultUrlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwp
 * @create 2019/07/15 12:42
 */
@Configuration
public class DefaultFilterConfig {
	@Bean
	public FilterRegistrationBean defaultURLFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setFilter(new DefaultUrlFilter());
		return filterRegistrationBean;
	}
}
