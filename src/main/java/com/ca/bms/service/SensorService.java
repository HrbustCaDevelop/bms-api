package com.ca.bms.service;

import com.ca.bms.entitys.SensorEntity;

/**
 * @author：刘志龙
 * @since：2014年12月4日 上午11:59:34
 * @version:1.0
 */
public interface SensorService {

	/**
	 * 后台添加传感器
	 * @param sensorEntity
	*/
	void saveSensorByObject(SensorEntity sensorEntity);
}
