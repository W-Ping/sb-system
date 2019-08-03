package com.ping.constant;

/**
 * 错误码
 *
 * @author lwp
 * @create 2019/07/28 9:48
 */
public enum ResultEnum {
	/**
	 *
	 */
	SUCCESS(200, "操作成功！"),
	FAIL(500, "操作失败！"),
	UNLOGIN_ERROR(1003, "用户未登录"),
	LOGIN_FAIL(2003, "用户名或密码错误"),

	REQ_PARAMETER_ERROR(4001, "请求参数错误"),

	SAVE_USER_INFO_FAILED(5001, "保存用户信息失败"),
	GET_USER_INFO_FAILED(5002, "获取用户信息失败"),
	UPDATE_BUDGETINFO_FAILED(5003, "更新材料信息失败"),
	SAVE_BUDGETINFO_FAILED(5004, "保存装修方案信息失败"),
	DATA_FAILED(6000, "数据异常"),
	HOUSE_IS_NOT_EXISTS(6001, "房屋信息不存在"),
	DATA_NOT_EXISTS(6002, "数据不存在");

	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
