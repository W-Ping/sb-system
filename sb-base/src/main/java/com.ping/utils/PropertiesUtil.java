package com.ping.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lwp
 * @create 2019/07/15 13:38
 */
public class PropertiesUtil {
	private static Map<String, String> propertiesMap = null;

	/**
	 * @param propertiesFileName
	 */
	public static void loadAllProperties(String propertiesFileName) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties(propertiesFileName);
			processProperties(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String name) {
		return propertiesMap.get(name) == null ? "" : propertiesMap.get(name);
	}

	public static Map<String, String> getAllProperty() {
		return propertiesMap;
	}

	private static void processProperties(Properties properties) {
		propertiesMap = new HashMap<>();
		for (Object key : properties.keySet()) {
			String keyStr = key.toString();
			try {
				propertiesMap.put(keyStr, new String(properties.getProperty(keyStr).getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
