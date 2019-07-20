package com.ping.exception;

/**
 * 运行异常
 *
 * @author lwp
 * @create 2019/07/13 21:20
 */
public class SBRuntimeException extends RuntimeException {
	/**
	 *
	 */
	public SBRuntimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public SBRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public SBRuntimeException(String message, Throwable e) {
		super(message, e);
	}
}
