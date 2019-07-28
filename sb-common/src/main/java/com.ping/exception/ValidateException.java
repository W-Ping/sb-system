package com.ping.exception;

import com.ping.constant.ResultEnum;

/**
 * 验证数据异常
 *
 * @author lwp
 * @create 2019/07/28 10:31
 */
public class ValidateException extends BaseBusinessException {
	public ValidateException(final ResultEnum resultEnum) {
		super(resultEnum);
	}

	public ValidateException(final ResultEnum resultEnum, final String error) {
		super(resultEnum, error);
	}
}
