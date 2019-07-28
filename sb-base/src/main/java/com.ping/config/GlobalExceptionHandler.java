package com.ping.config;

import com.ping.Result;
import com.ping.exception.BaseBusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author lwp
 * @create 2019/07/28 9:42
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	public Object globalExceptionHandler(Exception e) {
		logger.error("system exception {}", e);
		return Result.fail();
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler({BaseBusinessException.class})
	public Result BusinessExceptionHandler(BaseBusinessException e) {
		logger.error("business exception :{}", e);
		return Result.fail(e.getCode(), e.getMessage(), null);
	}

}
