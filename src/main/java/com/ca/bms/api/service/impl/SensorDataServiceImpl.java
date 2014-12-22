package com.ca.bms.api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.api.dao.SensorDataDao;
import com.ca.bms.api.service.SensorDataService;
import com.ca.bms.common.entitys.SensorDataEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;

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
		List<SensorDataEntity> tempList =  sensorDataDao.getSensorDataBySensorUUIDAndDatetime(serialNum, temp, 1);
		if (tempList.isEmpty()) {
			return null;
		}else {
			return tempList.get(0);
		}
		
	}

	@Override
	public List<SensorDataEntity> getHistoryDataBySerialNum(String timastamp,
			String serialNum) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date temp = sdf.parse(timastamp);
		List<SensorDataEntity> tempList =  sensorDataDao.getSensorDataBySensorUUIDAndDatetime(serialNum, temp, null);
		return tempList.isEmpty()?null:tempList;
	}

}
