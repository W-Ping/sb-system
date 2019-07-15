package com.ping;

import com.ping.constant.SysConstant;

/**
 * 返回结果
 *
 * @author lwp
 * @create 2019/07/13 15:07
 */
public class Result<T> {
	private String code;
	private String message;
	private T obj;

	public static <T> Result<T> success(T obj) {
		return success("request is success", obj);
	}

	public static <T> Result<T> success(String message, T obj) {
		Result<T> result = new Result<>();
		result.setCode(SysConstant.SUCCESS_CODE);
		result.setMessage(message);
		result.setObj(obj);
		return result;
	}

	public static <T> Result<T> successOrFial(boolean bool, T obj) {
		return bool ? success(obj) : fail(obj);
	}

	public static <T> Result<T> successOrFial(boolean bool, String message, T obj) {
		return bool ? success(obj) : fail(message, obj);
	}

	public static <T> Result<T> fail(T obj) {
		return fail("request is fail", obj);
	}

	public static <T> Result<T> fail(String message, T obj) {
		Result<T> result = new Result<>();
		result.setCode(SysConstant.FAIL_CODE);
		result.setMessage(message);
		result.setObj(obj);
		return result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(final T obj) {
		this.obj = obj;
	}
}