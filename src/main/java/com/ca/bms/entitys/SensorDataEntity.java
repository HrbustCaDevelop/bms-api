package com.ca.bms.entitys;

import java.io.Serializable;

/**
 * 传感器数据实体
 * @author：刘志龙
 * @since：2014年11月28日 下午6:02:23
 * @version:1.0
 */
public class SensorDataEntity implements Serializable{
	private static final long serialVersionUID = 5088735295312561869L;
	private Long id;
	private String createTime;
	private String modifyTime;
	private int version;
	private double temperature;
	private double humidity;
	private double co;
	private double smoke;
	private String serialNum;
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
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getCo() {
		return co;
	}
	public void setCo(double co) {
		this.co = co;
	}
	public double getSmoke() {
		return smoke;
	}
	public void setSmoke(double smoke) {
		this.smoke = smoke;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	@Override
	public String toString() {
		return "SensorDataEntity [id=" + id + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", co=" + co + ", smoke=" + smoke
				+ ", serialNum=" + serialNum + "]";
	}
}
