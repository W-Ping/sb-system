package com.ping.annotation;

import com.ping.constant.CacheKeyRuleEnum;
import com.ping.constant.CacheTypeEnum;

import java.lang.annotation.*;

/**
 * @author lwp
 * @create 2019/07/15 17:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheRemove {
	/**
	 * @return
	 */
	String key();

	/**
	 * @return
	 */
	CacheTypeEnum cacheType() default CacheTypeEnum.LOCAL_CACHE;
	/**
	 * @return
	 */
	CacheKeyRuleEnum cacheKeyRule() default CacheKeyRuleEnum.METHOD_SIGNATURE;
}
