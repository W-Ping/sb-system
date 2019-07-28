package com.ping.exception;

import com.ping.constant.ResultEnum;

/**
 * @author lwp
 * @create 2019/07/28 11:06
 */
public class OptionsException extends BaseBusinessException {
	public OptionsException(final ResultEnum resultEnum) {
		super(resultEnum);
	}

	public OptionsException(final ResultEnum resultEnum, final String error) {
		super(resultEnum, error);
	}
}
