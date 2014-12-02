package com.ca.bms.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午11:52:07
 * @version:1.0
 */
public enum SensorDataStatusEnum {
	PI("PARAM_ILLEGAL", "参数错误"), 
	DFS("DATA_FETCH_SUCCESS", "数据获取成功"),
	DIS("DATA_INSERT_SUCCESS", "数据接收成功"),
	DIF("DATA_INSERT_FAILED", "数据接收失败");

	private static Map<String, SensorDataStatusEnum> valueMap = new HashMap<String, SensorDataStatusEnum>();

	private String value;
	private String displayName;

	static {
		for (SensorDataStatusEnum item : SensorDataStatusEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	SensorDataStatusEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static SensorDataStatusEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
