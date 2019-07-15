package com.ping.utils;

import org.springframework.util.DigestUtils;

/**
 * @author lwp
 * @create 2019/07/15 20:47
 */
public class DigestUtil {
	/**
	 * @param str
	 * @return
	 */
	public static String md5DoubleEncrypt(String str) {
		if (str == null || str.length() <= 0) {
			return null;
		}
		return DigestUtils.md5DigestAsHex(DigestUtils.md5Digest(str.getBytes()));
	}
}
