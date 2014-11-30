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
	ACBU("ACCOUNT_CAN_BE_USE", "该用户名可以被使用"),
	RF("REGISTER_FAIL", "注册失败"), 
	PI("PARAM_ILLEGAL", "参数有误"), 
	RS("REGISTER_SUCCESS", "注册成功"),
	LS("LOGIN_SUCCESS", "登陆成功"),
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
