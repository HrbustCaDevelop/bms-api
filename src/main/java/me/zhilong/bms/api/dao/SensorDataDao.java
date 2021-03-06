package me.zhilong.bms.api.dao;

import java.util.Date;
import java.util.List;

import me.zhilong.bms.common.entitys.SensorDataEntity;

import org.apache.ibatis.annotations.Param;

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
	List<SensorDataEntity> getSensorDataBySensorUUIDAndDatetime(@Param("serialnum")String serialNum , @Param("timecut")Date timecut , @Param("num")Integer num);
}
