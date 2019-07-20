package com.ping.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liu_wp
 * @date Created in 2019/3/6 16:58
 * @see
 */
public class FieldUtil {


	/**
	 * @param cls
	 * @return
	 */
	public static List<Field> getObjectField(Class<?> cls) {
		List<Field> fieldList = new ArrayList<>();
		Class tempClass = cls;
		boolean isFirst = true;
		while (tempClass != null) {
			//只认子类的字段
			Field[] declaredFields = tempClass.getDeclaredFields();
			tempClass = tempClass.getSuperclass();
			if (tempClass == null && isFirst) {
				fieldList.addAll(Arrays.asList(declaredFields));
				break;
			} else {
				for (int i = 0; i < declaredFields.length; i++) {
					Field declaredField = declaredFields[i];
					boolean b = fieldList.stream().anyMatch(v -> v.getName().equals(declaredField.getName()));
					if (!b) {
						fieldList.add(declaredFields[i]);
					}
				}
				isFirst = false;
			}
		}
		return fieldList;
	}

	/**
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> List<Field> getObjectAllField(Class<T> cls) {
		List<Field> fields = null;
		Map<String, Field> fieldAllMap = getObjectAllFieldMap(cls);
		if (fieldAllMap != null && fieldAllMap.size() > 0) {
			fields = fieldAllMap.values().stream().collect(Collectors.toList());
		}
		return fields;
	}

	/**
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> Map<String, Field> getObjectAllFieldMap(Class<T> cls) {
		Map<String, Field> fieldAllMap = new HashMap<>();
		Class<? super T> superclass = cls;
		do {
			if (superclass == null) {
				break;
			}
			Field[] declaredFields = superclass.getDeclaredFields();
			if (declaredFields != null && declaredFields.length > 0) {
				Map<String, Field> fieldMap = Arrays.stream(declaredFields).filter(v -> !"serialVersionUID".equals(v.getName()) && !fieldAllMap.containsKey(v.getName())).collect(Collectors.toMap(v -> v.getName(), Function.identity(), (k1, k2) -> k1));
				if (fieldMap != null) {
					fieldAllMap.putAll(fieldMap);
				}
			}
			superclass = superclass.getSuperclass();
		} while (true);
		return fieldAllMap;
	}

	/**
	 * @param instance
	 * @param field
	 * @param value
	 * @param <T>
	 * @throws Exception
	 */
	public static <T> void setValueToField(T instance, Field field, Object value) throws Exception {
		if (value == null || instance == null || field == null) {
			return;
		}
		field.setAccessible(true);
		Class<?> type = field.getType();
		if (type.equals(String.class)) {
			field.set(instance, value);
		} else if (type.equals(Double.class)) {
			field.set(instance, Double.parseDouble(value.toString()));
		} else if (type.equals(Integer.class)) {
			field.set(instance, Integer.valueOf(String.valueOf(value)));
		} else if (type.equals(Float.class)) {
			field.set(instance, Float.valueOf(value.toString()));
		} else if (type.equals(Boolean.class)) {
			field.set(instance, Boolean.valueOf(value.toString()));
		} else if (type.equals(Character.class)) {
			field.set(instance, (char) value);
		} else if (type.equals(Long.class)) {
			field.set(instance, Long.parseLong(value.toString()));
		} else if (type.equals(BigDecimal.class)) {
			field.set(instance, new BigDecimal(value.toString()));
		} else if (type.equals(Date.class)) {
			SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date date = sf.parse(value.toString());
			field.set(instance, date);
		}

	}
}
