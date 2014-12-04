package com.ca.bms.entitys;

import java.io.Serializable;

/**
 * 传感器实体
 * @author：刘志龙
 * @since：2014年11月28日 下午6:00:17
 * @version:1.0
 */
public class SensorEntity implements Serializable {
	private static final long serialVersionUID = -1585593241693729171L;
	private Long id;
	private String createTime;
	private String modifyTime;
	private int version;
	private String serialNum;
	private Long userId;
	private String sensorType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSensorType() {
		return sensorType;
	}
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	@Override
	public String toString() {
		return "SensorEntity [id=" + id + ", serialNum=" + serialNum
				+ ", userId=" + userId + ", sensorType=" + sensorType + "]";
	}
}
