package me.zhilong.bms.api.service;

import java.util.List;

import me.zhilong.bms.common.entitys.AlertMsgEntity;
import me.zhilong.bms.common.enumtype.SensorDataStatusEnum;

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
