package com.ca.bms.service;

import com.ca.bms.entitys.SensorDataEntity;
import com.ca.bms.enumtype.SensorDataStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午11:55:02
 * @version:1.0
 */
public interface SensorDataService {
	/**
	 * 保存传感器数据
	 * @param sensorDataEntity
	 * @return
	*/
	SensorDataStatusEnum savaSensorData(SensorDataEntity sensorDataEntity);
}
