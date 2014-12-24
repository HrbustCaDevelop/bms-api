package com.ca.bms.api.controllers;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ca.bms.api.annotation.AuthPass;
import com.ca.bms.api.service.AlertMsgService;
import com.ca.bms.api.service.SensorDataService;
import com.ca.bms.common.entitys.AlertMsgEntity;
import com.ca.bms.common.entitys.SensorDataEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午10:48:16
 * @version:1.0
 */
@Controller
@RequestMapping(value = "/sensordata")
public class SensorDataController {

	private static Logger logger = Logger.getLogger(SensorDataController.class);
	private static SimplePropertyPreFilter sensorDataFilter = new SimplePropertyPreFilter(
			SensorDataEntity.class, "createTime", "temperature", "humidity", "co", "smoke", "sensorAddr");
	private static SimplePropertyPreFilter AlertDataFilter = new SimplePropertyPreFilter(
			AlertMsgEntity.class, "createTime");
	@Autowired
	SensorDataService sensorDataService;

	@Autowired
	AlertMsgService alertMsgService;
	
	/**
	 * 添加一条传感器数据
	 * 
	 * @param temperature
	 * @param humidity
	 * @param co
	 * @param smoke
	 * @param serialnum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object addSensorData(
			@RequestParam(value = "temperature", required = true) double temperature,
			@RequestParam(value = "humidity", required = true) double humidity,
			@RequestParam(value = "co", required = true) double co,
			@RequestParam(value = "smoke", required = true) double smoke,
			@RequestParam(value = "serialnum", required = true) String serialnum) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		if (serialnum.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		SensorDataEntity sensorDataEntity = new SensorDataEntity();
		sensorDataEntity.setHumidity(humidity);
		sensorDataEntity.setTemperature(temperature);
		sensorDataEntity.setCo(co);
		sensorDataEntity.setSmoke(smoke);
		sensorDataEntity.setSerialNum(serialnum);
		regMsg.append(sensorDataService.savaSensorData(sensorDataEntity)
				.getValue());
		regMsg.append("\"}");
		logger.info("Receive User Add Request! :" + sensorDataEntity.toString());
		return regMsg.toString();
	}

	/**
	 * 获取实时数据
	 * 
	 * @param username
	 * @param serialnum
	 * @param usertoken
	 * @param request
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/realtime", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getRealtimeSensorData(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "serialnum", required = true) String serialnum,
			@RequestParam(value = "usertoken", required = true) String usertoken) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		if (serialnum.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		SensorDataEntity sde = null;
		try {
			sde = sensorDataService.getRealTimeDataBySerialNum(serialnum);
			if (sde == null) {
				regMsg.append(SensorDataStatusEnum.PI.getValue());
				regMsg.append("\"}");
				return regMsg.toString();
			} else {
				regMsg.append(SensorDataStatusEnum.DFS.getValue());
			}
		} catch (ParseException e) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		regMsg.append("\",\"data\":");
		regMsg.append(JSON.toJSONString(sde, sensorDataFilter));
		logger.info("返回一条实时数据! :" + sde.toString());
		regMsg.append("}");
		return regMsg.toString();
	}

	/**
	 * 获取历史数据
	 * 
	 * @param username
	 * @param serialnum
	 * @param usertoken
	 * @param timestamp
	 * @param session
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/history", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getHistorySensorData(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "serialnum", required = true) String serialnum,
			@RequestParam(value = "usertoken", required = true) String usertoken,
			@RequestParam(value = "timestamp", required = true) String timestamp) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");

		if (serialnum.trim().equals("") || timestamp.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}

		List<SensorDataEntity> tempList = null;
		try {
			tempList = sensorDataService.getHistoryDataBySerialNum(timestamp,
					serialnum);
			if (tempList == null) {
				regMsg.append(SensorDataStatusEnum.PI.getValue());
				regMsg.append("\"}");
				return regMsg.toString();
			} else {
				regMsg.append(SensorDataStatusEnum.DFS.getValue());
			}
		} catch (ParseException e) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		regMsg.append("\",\"data\":");
		regMsg.append(JSON.toJSONString(tempList, sensorDataFilter));
		logger.info("返回一堆历史数据! :" + tempList.toString());
		regMsg.append("}");
		return regMsg.toString();
	}
	
	/**
	 * 保存警报信息
	 * @param serialnum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/alert/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object alertAdd(
			@RequestParam(value = "serialNum", required = true) String serialnum) {
		StringBuilder returnMsg = new StringBuilder("{\"returnmsg\":\"");
		if (serialnum.trim().equals("")) {
			returnMsg.append(SensorDataStatusEnum.PI.getValue());
			returnMsg.append("\"}");
			return returnMsg.toString();
		}
		AlertMsgEntity entity = new AlertMsgEntity();
		entity.setSerialNum(serialnum);
		returnMsg.append(alertMsgService.saveAlertMsg(entity)
				.getValue());
		returnMsg.append("\"}");
		return returnMsg.toString();
	}
	
	/**
	 * 查询警报信息
	 * @param serialnum
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/alert/get", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object alertGet(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "serialnum", required = true) String serialnum,
			@RequestParam(value = "usertoken", required = true) String usertoken) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		if (serialnum.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		List<AlertMsgEntity> tempList;
		try {
			tempList = alertMsgService.getAlertMsg(serialnum);
			if (null == tempList) {
				regMsg.append(SensorDataStatusEnum.PI.getValue());
				regMsg.append("\"}");
				return regMsg.toString();
			} else {
				regMsg.append(SensorDataStatusEnum.DFS.getValue());
			}
		} catch (Exception e) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		regMsg.append("\",\"data\":");
		regMsg.append(JSON.toJSONString(tempList, AlertDataFilter));
		regMsg.append("}");
		return regMsg.toString();
	}
}
