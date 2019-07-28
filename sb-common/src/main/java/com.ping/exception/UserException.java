package com.ping.exception;

import com.ping.constant.ResultEnum;

/**
 * 用户异常
 *
 * @author lwp
 * @create 2019/07/28 10:28
 */
public class UserException extends BaseBusinessException {
	public UserException(final ResultEnum resultEnum) {
		super(resultEnum);
	}

	public UserException(final ResultEnum resultEnum, final String error) {
		super(resultEnum, error);
	}
}
