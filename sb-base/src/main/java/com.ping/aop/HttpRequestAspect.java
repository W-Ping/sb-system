package com.ping.aop;

import com.ping.annotation.CacheRemove;
import com.ping.annotation.CacheSave;
import com.ping.config.CacheConfig;
import com.ping.utils.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lwp
 * @create 2019/07/15 16:32
 */
@Aspect
@Component
public class HttpRequestAspect {
	@Autowired
	private CacheConfig cacheConfig;
	private final Logger LOGGER = LoggerFactory.getLogger(HttpRequestAspect.class);

	@Pointcut(value = "@annotation(cacheSave)", argNames = "cacheSave")
	public void cacheSavePointcut(CacheSave cacheSave) {
	}

	@Pointcut(value = "@annotation(cacheRemove)", argNames = "cacheRemove")
	public void cacheRemovePointcut(CacheRemove cacheRemove) {
	}

	@Around("cacheSavePointcut(cacheSave)")
	public Object cacheSaveAround(ProceedingJoinPoint joinPoint, CacheSave cacheSave) throws Throwable {
		Object result = joinPoint.proceed();
		LOGGER.info("cacheSave result{}", JSONUtil.objectToString(result));
		cacheConfig.put(result, cacheSave, joinPoint);
		return result;
	}

	@Around("cacheRemovePointcut(cacheRemove)")
	public Object cacheRemoveAround(ProceedingJoinPoint joinPoint, CacheRemove cacheRemove) throws Throwable {
		Object result = joinPoint.proceed();
		LOGGER.info("cacheSave result{}", JSONUtil.objectToString(result));
		cacheConfig.remove(cacheRemove, joinPoint);
		return result;
	}

}
