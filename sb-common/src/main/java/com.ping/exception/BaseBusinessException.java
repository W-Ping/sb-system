package com.ping.exception;

import com.ping.constant.ResultEnum;

/**
 * 异常类
 *
 * @author lwp
 * @create 2019/07/28 10:26
 */
public class BaseBusinessException extends RuntimeException {
	private Integer code;
	private String message;

	public BaseBusinessException(ResultEnum resultEnum) {
		this(resultEnum.getMessage(), resultEnum.getCode());
	}

	public BaseBusinessException(ResultEnum resultEnum, String error) {
		this(error != null && error.length() > 0 ? resultEnum.getMessage() + "【" + error + "】" : resultEnum.getMessage(), resultEnum.getCode());
	}

	private BaseBusinessException(String message, Integer code) {
		super(message);
		this.message=message;
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
