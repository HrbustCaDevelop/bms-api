package me.zhilong.bms.api.service.impl;

import java.util.List;

import me.zhilong.bms.api.dao.AlertMsgDao;
import me.zhilong.bms.api.dao.UserDao;
import me.zhilong.bms.api.service.AlertMsgService;
import me.zhilong.bms.common.entitys.AlertMsgEntity;
import me.zhilong.bms.common.enumtype.SensorDataStatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
