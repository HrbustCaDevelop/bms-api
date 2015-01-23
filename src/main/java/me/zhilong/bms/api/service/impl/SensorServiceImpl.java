package me.zhilong.bms.api.service.impl;

import me.zhilong.bms.api.dao.SensorDao;
import me.zhilong.bms.api.service.SensorService;
import me.zhilong.bms.common.entitys.SensorEntity;
import me.zhilong.bms.common.enumtype.SensorStatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
