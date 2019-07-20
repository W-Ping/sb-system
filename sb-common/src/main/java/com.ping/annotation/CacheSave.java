package com.ping.annotation;

import com.ping.constant.CacheKeyRuleEnum;
import com.ping.constant.CacheTypeEnum;

import java.lang.annotation.*;

/**
 * @author lwp
 * @create 2019/07/15 16:51
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheSave {
	/**
	 * @return
	 */
	String key() default "";

	/**
	 * @return
	 */
	String value() default "";

	/**
	 * @return
	 */
	CacheTypeEnum cacheType() default CacheTypeEnum.LOCAL_CACHE;

	/**
	 * @return
	 */
	CacheKeyRuleEnum cacheKeyRule() default CacheKeyRuleEnum.METHOD_SIGNATURE;


}
