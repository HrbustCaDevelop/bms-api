package com.ca.bms.api.dao;

import java.util.List;

import com.ca.bms.common.entitys.AlertMsgEntity;

public interface AlertMsgDao {
	
	/**
	 * 保存警报消息
	 * @param entity
	 * @return
	 */
	int saveAlertDataByObject(AlertMsgEntity entity);
	
	/**
	 * 获取所有的警报信息
	 * @return
	 */
	List<AlertMsgEntity> getAlertMsgBySerialNum(String serialnum);
}
