package com.ping.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ping.constant.ResultEnum;
import com.ping.exception.OptionsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 对象转换工具
 *
 * @author lwp
 * @create 2019/07/13 15:45
 */
public class BeanMapperUtil {
	private final static Logger logger = LogManager.getLogger(BeanMapperUtil.class);
	private static final String SYMBOL = "_";
	private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

	/**
	 * @param sources
	 * @param destinationClass
	 * @param <T>
	 * @return
	 */
	public static <T> T map(Object sources, Class<T> destinationClass) {
		if (sources == null) {
			return null;
		}
		return MAPPER.map(sources, destinationClass);
	}

	/**
	 * @param sources
	 * @param destination
	 */
	public static void map(Object sources, Object destination) {
		MAPPER.map(sources, destination);
	}

	/**
	 * @param sourcesList
	 * @param destinationClass
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> mapToList(Collection sourcesList, Class<T> destinationClass) {
		List<T> destinationList = new ArrayList<>();
		if (sourcesList != null && sourcesList.size() > 0) {
			T map;
			for (final Object o : sourcesList) {
				map = MAPPER.map(o, destinationClass);
				if (map != null) {
					destinationList.add(map);
				}
			}
		}
		return destinationList;
	}

	/**
	 * @param list
	 * @param cls
	 * @param <V>
	 * @param <T>
	 * @return
	 */
	public static <V, T> List<T> mapListToObjectList(List<Map<String, V>> list, Class<T> cls) {
		List<T> resultList = new ArrayList<>();
		if (list != null && list.size() > 0 && cls != null) {
			for (Map<String, V> source : list) {
				T t = mapToObject(source, cls, true);
				if (t != null) {
					resultList.add(t);
				}
			}
		}
		return resultList;
	}

	/**
	 * @param source
	 * @param cls
	 * @param toCamelCase
	 * @param <V>
	 * @param <T>
	 * @return
	 */
	public static <V, T> T mapToObject(Map<String, V> source, Class<T> cls, boolean toCamelCase) {
		if (source == null || cls == null) {
			return null;
		}
		try {
			Map<String, Field> objectAllFieldMap = FieldUtil.getObjectAllFieldMap(cls);
			T instance = cls.newInstance();
			for (Map.Entry<String, V> map : source.entrySet()) {
				String key = toCamelCase(map.getKey(), toCamelCase);
				V value = map.getValue();
				Field field = objectAllFieldMap.get(key);
				FieldUtil.setValueToField(instance, field, value);
			}
			return instance;
		} catch (Exception e) {
			logger.error("数据转换异常{}", e);
			throw new OptionsException(ResultEnum.DATA_FAILED, "map cast to object error!");
		}
	}


	/**
	 * @param name
	 * @return
	 */
	public static String toCamelCase(String name) {
		return toCamelCase(name, true);
	}

	/**
	 * @param name
	 * @param flag
	 * @return
	 */
	private static String toCamelCase(String name, boolean flag) {
		if (name.indexOf(SYMBOL) != -1 && flag) {
			String[] strArr = name.split("_");
			StringBuilder sb = new StringBuilder();
			String s;
			for (int i = 0; i < strArr.length; i++) {
				s = strArr[i];
				if (i == 0) {
					s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
				} else {
					s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
				}
				sb.append(s);
			}
			name = sb.toString();
		}
		return name;
	}
}
