package com.ca.bms.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.api.dao.SensorDao;
import com.ca.bms.api.service.SensorService;
import com.ca.bms.common.entitys.SensorEntity;
import com.ca.bms.common.enumtype.SensorStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年12月4日 下午12:01:06
 * @version:1.0
 */
@Service(value="sensorService")
public class SensorServiceImpl implements SensorService {

	@Autowired
	SensorDao sensorDao;
	
	@Override
	public SensorStatusEnum saveSensorByObject(SensorEntity sensorEntity) {
		if (sensorDao.getSensorBySerialNum(sensorEntity.getSerialNum()) == null) {
			sensorDao.saveSensorBySensor(sensorEntity);
			return SensorStatusEnum.SS;
		}else {
			return SensorStatusEnum.SIE;
		}
	}

}
