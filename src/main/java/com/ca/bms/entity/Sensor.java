package com.ca.bms.entity;

/**
 * 传感器实体
 * @author：刘志龙
 * @since：2014年11月28日 下午6:00:17
 * @version:1.0
 */
public class Sensor {
	private Integer id;
	private String createTime;
	private String modifyTime;
	private int version;
	private String sersorUUID;
	private int userId;
	private String sensorType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getSersorUUID() {
		return sersorUUID;
	}

	public void setSersorUUID(String sersorUUID) {
		this.sersorUUID = sersorUUID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
}
