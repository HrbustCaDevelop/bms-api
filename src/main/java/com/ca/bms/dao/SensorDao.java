package com.ca.bms.dao;

import com.ca.bms.entitys.Sensor;

/**
 * 传感器dao
 * @author：刘志龙
 * @since：2014年11月28日 下午6:17:37
 * @version:1.0
 */
public interface SensorDao {
	/**
	 * 保存传感器信息
	 * @param sensor
	*/
	void saveSensorBySensor(Sensor sensor);
	/**
	 * 删除传感器信息
	 * @param sensor
	*/
	void delSensorBySensor(Sensor sensor);
}
