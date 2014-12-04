package com.ca.bms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.dao.SensorDao;
import com.ca.bms.entitys.SensorEntity;
import com.ca.bms.service.SensorService;

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
	public void saveSensorByObject(SensorEntity sensorEntity) {
		sensorDao.saveSensorBySensor(sensorEntity);
	}

}
