package com.ping;

import com.ping.constant.ResultEnum;

/**
 * 返回结果
 *
 * @author lwp
 * @create 2019/07/13 15:07
 */
public class Result<T> {
	private int code;
	private String message;
	private T obj;

	public Result(int code, String message, T obj) {
		this.code = code;
		this.message = message;
		this.obj = obj;
	}

	public Result(int code, String message) {
		this(code, message, null);
	}

	public static <T> Result<T> success(int code, String message, T obj) {
		return new Result<>(code, message, obj);
	}

	public static <T> Result<T> success(T obj) {
		ResultEnum success = ResultEnum.SUCCESS;
		return Result.success(success.getCode(), success.getMessage(), obj);
	}

	public static <T> Result<T> success() {
		return Result.success(null);
	}

	public static <T> Result<T> fail(int code, String message, T obj) {
		return new Result<>(code, message, obj);
	}

	public static <T> Result<T> fail(T obj) {
		ResultEnum fail = ResultEnum.FAIL;
		return Result.fail(fail.getCode(), fail.getMessage(), obj);
	}

	public static <T> Result<T> fail() {
		return Result.fail(null);
	}

	public static <T> Result<T> successOrFail(boolean bool) {
		return Result.successOrFail(bool, null);
	}

	public static <T> Result<T> successOrFail(boolean bool, T obj) {
		return bool ? Result.success(obj) : Result.fail(obj);
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
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
