package com.ca.bms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.dao.SensorDataDao;
import com.ca.bms.entitys.SensorDataEntity;
import com.ca.bms.enumtype.SensorDataStatusEnum;
import com.ca.bms.service.SensorDataService;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午11:57:50
 * @version:1.0
 */
@Service(value="sensorDataService")
public class SensorDataServiceImpl implements SensorDataService {

	private static Logger logger = Logger.getLogger(SensorDataServiceImpl.class);
	
	@Autowired
	SensorDataDao sensorDataDao;
	
	@Override
	public SensorDataStatusEnum savaSensorData(SensorDataEntity sensorDataEntity) {
		if (sensorDataEntity == null) {
			return SensorDataStatusEnum.PI;
		}
		try {
			if (sensorDataEntity.getSerialNum().trim().equals("")) {
					return SensorDataStatusEnum.PI;
				}
		} catch (Exception e) {
			return SensorDataStatusEnum.PI;
		}
		try {
			if (sensorDataDao.savaSensorDataByObject(sensorDataEntity) > 0) {
				logger.info("A SensorData Sava Successful :" + sensorDataEntity.toString());
				return SensorDataStatusEnum.DIS;
			} else {
				return SensorDataStatusEnum.DIF;
			}
		} catch (Exception e) {
			return SensorDataStatusEnum.DIF;
		}
	}

	@Override
	public SensorDataEntity getRealTimeDataBySerialNum(String serialNum) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date temp = sdf.parse("1000-01-01 01:01:01");  
	    sdf.format(temp);  
		return sensorDataDao.getSensorDataBySensorUUIDAndDatetime(serialNum, temp, 1);
	}

}
