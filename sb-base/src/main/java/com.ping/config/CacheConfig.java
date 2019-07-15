package com.ping.config;

import com.ping.annotation.CacheRemove;
import com.ping.annotation.CacheSave;
import com.ping.constant.CacheKeyRuleEnum;
import com.ping.constant.CacheTypeEnum;
import com.ping.utils.DigestUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lwp
 * @create 2019/07/15 17:29
 */
@Component
public class CacheConfig {
	@Autowired
	private HttpSession session;
	private final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);
	/**
	 * 用于SpEL表达式解析.
	 */
	private final SpelExpressionParser parser = new SpelExpressionParser();
	/**
	 * 用于获取方法参数定义名字.
	 */
	private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

	/**
	 * 本地缓存
	 */
	private static Map<String, Object> localCache = null;

	/**
	 * @param value
	 * @param cacheSave
	 */
	public void put(Object value, CacheSave cacheSave, ProceedingJoinPoint joinPoint) {
		if (cacheSave == null) {
			return;
		}
		String value1 = cacheSave.value();
		Object cacheValue = value;
		if (StringUtils.isNotBlank(value1)) {
			cacheValue = value1;
		}
		String key = getCacheKey(cacheSave.key(), joinPoint, cacheSave.cacheKeyRule());
		LOGGER.info("cache key is 【{}】", key);
		CacheTypeEnum cacheTypeEnum = cacheSave.cacheType();
		if (CacheTypeEnum.LOCAL_CACHE.equals(cacheTypeEnum)) {
			if (localCache == null) {
				synchronized (this) {
					localCache = new ConcurrentHashMap<>();
				}
			}
			localCache.put(key, cacheValue);
		} else if (CacheTypeEnum.SESSION_CACHE.equals(cacheTypeEnum)) {
			session.setAttribute(key, cacheValue);
		} else if (CacheTypeEnum.REDIS_CACHE.equals(cacheTypeEnum)) {
			//扩展redis
		}
	}

	/**
	 * @param cacheRemove
	 */
	public void remove(CacheRemove cacheRemove, ProceedingJoinPoint joinPoint) {
		if (cacheRemove == null) {
			return;
		}
		String key = getCacheKey(cacheRemove.key(), joinPoint, cacheRemove.cacheKeyRule());
		CacheTypeEnum cacheTypeEnum = cacheRemove.cacheType();
		if (CacheTypeEnum.LOCAL_CACHE.equals(cacheTypeEnum)) {
			if (localCache != null) {
				localCache.remove(key);
			}
		} else if (CacheTypeEnum.SESSION_CACHE.equals(cacheTypeEnum)) {
			session.removeAttribute(key);
		} else if (CacheTypeEnum.REDIS_CACHE.equals(cacheTypeEnum)) {
			//扩展redis
		}
	}

	/**
	 * @param key
	 * @param joinPoint
	 * @param cacheKeyRuleEnum
	 * @return
	 */
	private String getCacheKey(String key, ProceedingJoinPoint joinPoint, CacheKeyRuleEnum cacheKeyRuleEnum) {
		String sk = "";
		if (StringUtils.isBlank(key) || CacheKeyRuleEnum.METHOD_SIGNATURE.equals(cacheKeyRuleEnum)) {
			sk = generateKey(joinPoint);
		} else if (CacheKeyRuleEnum.SPEL.equals(cacheKeyRuleEnum)) {
			sk = generateKeyBySpEL(key, joinPoint);
		}
		if (!CacheKeyRuleEnum.NORMAL.equals(cacheKeyRuleEnum)
				&& StringUtils.isNotBlank(key)) {
			sk = key + ":" + sk;
		}
		return sk;
	}

	/**
	 * @param joinPoint
	 * @return
	 */
	private String generateKey(ProceedingJoinPoint joinPoint) {
		Class itsClass = joinPoint.getTarget().getClass();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(itsClass.getName());
		keyBuilder.append(".").append(methodSignature.getName());
		keyBuilder.append("{");
		for (Object arg : joinPoint.getArgs()) {
			keyBuilder.append(arg.getClass().getSimpleName() + arg + ":");
		}
		keyBuilder.append("}");
		String key = DigestUtil.md5DoubleEncrypt(keyBuilder.toString());
		return key;
	}

	/**
	 * key = “’xxx’ + #arg”
	 *
	 * @param key
	 * @param joinPoint
	 * @return
	 */
	private String generateKeyBySpEL(String key, ProceedingJoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
		Expression expression = parser.parseExpression(key);
		EvaluationContext context = new StandardEvaluationContext();
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			context.setVariable(paramNames[i], args[i]);
		}
		return expression.getValue(context).toString();
	}

}
