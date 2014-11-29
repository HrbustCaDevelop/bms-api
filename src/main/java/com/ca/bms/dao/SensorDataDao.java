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
	 * 通过传感器UUID和时间范围获取传感器数据
	 * @param sensorUUID
	 * @param starttime
	 * @param stoptime
	 * @param num
	*/
	SensorDataEntity getSensorDataBySensorUUIDAndDatetime(int sensorUUID , Date starttime , Date stoptime , int num);
}
