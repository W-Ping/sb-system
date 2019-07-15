package com.ping.constant;

/**
 * @author lwp
 * @create 2019/07/15 19:37
 */
public enum CacheKeyRuleEnum {
	/**
	 * 自定义key
	 */
	NORMAL,
	/**
	 * SpEL 格式key @CacheSave(key = “’xxx’ + #arg”)
	 */
	SPEL,
	/**
	 * 方法签名
	 */
	METHOD_SIGNATURE;
}
