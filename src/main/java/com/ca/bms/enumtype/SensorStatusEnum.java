package com.ca.bms.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：刘志龙
 * @since：2014年12月4日 上午11:26:49
 * @version:1.0
 */
public enum SensorStatusEnum {
	SIR("SENSER_IS_REGISTER", "该序列号对应传感器已经被绑定"),
	SIE("SERIAL_IS_EXIST", "该序列号已经存在"),
	SS("SAVE_SUCCESS", "保存成功"),
	SF("SAVE_FAILED", "保存失败"),
	SINE("SERIAL_IS_NOT_EXIST", "序列号无效"),
	RF("REGISTER_FAIL", "绑定失败"),
	RS("REGISTER_SUCCESS", "传感器绑定成功");

	private static Map<String, SensorStatusEnum> valueMap = new HashMap<String, SensorStatusEnum>();

	private String value;
	private String displayName;

	static {
		for (SensorStatusEnum item : SensorStatusEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	SensorStatusEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static SensorStatusEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
