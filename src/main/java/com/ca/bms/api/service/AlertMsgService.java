package com.ca.bms.api.service;

import java.util.List;

import com.ca.bms.common.entitys.AlertMsgEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午11:55:02
 * @version:1.0
 */
public interface AlertMsgService {
	
	/**
	 * 获取所有的警报信息
	 * @return
	 */
	List<AlertMsgEntity> getAlertMsg(String serialnum);
	
	/**
	 * 保存警报信息
	 * @param entity
	 * @return
	 */
	SensorDataStatusEnum saveAlertMsg(AlertMsgEntity entity);
}
