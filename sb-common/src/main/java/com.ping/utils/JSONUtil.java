package com.ping.utils;

import com.alibaba.fastjson.JSON;

/**
 * @author liu_wp
 * @date Created in 2019/3/6 18:46
 * @see
 */
public class JSONUtil {


	/**
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		if (object == null) {
			return null;
		}
		return JSON.toJSONString(object);
	}



}
