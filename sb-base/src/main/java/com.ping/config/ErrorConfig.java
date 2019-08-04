package com.ping.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author lwp
 * @create 2019/07/23 23:39
 */
@Configuration
public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(final ErrorPageRegistry registry) {
		ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
		ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
		ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		registry.addErrorPages(error400Page, error401Page, error404Page,error500Page);
	}
}
