package com.ca.bms.dao;

import java.util.Date;

import com.ca.bms.entitys.SensorDataEntity;

/**
 * 传感器数据Dao
 * @author：刘志龙
 * @since：2014年11月28日 下午6:26:16
 * @version:1.0
 */
public interface SensorDataDao {

	/**
	 * 保存传感器数据
	 * @param sensorDataEntity
	 * @return
	*/
	int savaSensorDataByObject(SensorDataEntity sensorDataEntity);
	
	/**
	 * 通过传感器UUID和时间范围获取传感器数据
	 * @param sensorUUID
	 * @param timestamp
	 * @param num
	*/
	SensorDataEntity getSensorDataBySensorUUIDAndDatetime(String serialNum , Date timestamp , int num);
}
