package com.ca.bms.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午9:24:14
 * @version:1.0
 */
public enum UserStatusEnum {
	AIE("ACCOUNT_IS_EXIST", "该用户名已经存在"),
	ACBU("ACCOUNT_CAN_BE_USE", "该用户名未注册"),
	NAS("NO_ANY_SENSOR", "该用户尚未绑定传感器"),
	FS("FETCH_SUCCESS", "数据获取成功"),
	AF("AUTH_FAIL", "权限不足"),
	RF("REGISTER_FAIL", "注册失败"), 
	PI("PARAM_ILLEGAL", "参数错误"), 
	RS("REGISTER_SUCCESS", "注册成功"),
	LS("LOGIN_SUCCESS", "登陆成功"),
	UF("UPDATE_FAILED", "更新失败"),
	USNC("USER_MSG_NOT_CHANGE", "用户信息未更新"),
	US("UPDATE_SUCCESS", "更新成功"),
	UINI("USER_IS_NOT_INSIDE", "请先登陆"),
	UILO("USER_IS_LOGOUT", "用户已退出"),
	LF("LOGIN_FAILED", "用户名或密码有误");

	private static Map<String, UserStatusEnum> valueMap = new HashMap<String, UserStatusEnum>();

	private String value;
	private String displayName;

	static {
		for (UserStatusEnum item : UserStatusEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	UserStatusEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static UserStatusEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
