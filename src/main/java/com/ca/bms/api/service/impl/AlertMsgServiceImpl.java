package com.ca.bms.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.api.dao.AlertMsgDao;
import com.ca.bms.api.dao.UserDao;
import com.ca.bms.api.service.AlertMsgService;
import com.ca.bms.common.entitys.AlertMsgEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;

@Service("alertMsgService")
public class AlertMsgServiceImpl implements AlertMsgService {

	@Autowired
	AlertMsgDao alertMsgDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<AlertMsgEntity> getAlertMsg(String serialnum) {
		List<AlertMsgEntity> tempList =  alertMsgDao.getAlertMsgBySerialNum(serialnum);
		return tempList.isEmpty()?null:tempList;
	}

	@Override
	public SensorDataStatusEnum saveAlertMsg(AlertMsgEntity entity) {
		if (null == entity) {
			return SensorDataStatusEnum.PI;
		}
		try {
			if (entity.getSerialNum().trim().equals("")) {
					return SensorDataStatusEnum.PI;
				}
		} catch (Exception e) {
			return SensorDataStatusEnum.PI;
		}
		
		try {
			if (alertMsgDao.saveAlertDataByObject(entity) > 0) {
				return SensorDataStatusEnum.DIS;
			} else {
				return SensorDataStatusEnum.DIF;
			}
		} catch (Exception e) {
			return SensorDataStatusEnum.DIF;
		}
	}
}
