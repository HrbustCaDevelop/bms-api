package me.zhilong.bms.api.dao;

import java.util.List;

import me.zhilong.bms.common.entitys.AlertMsgEntity;

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
