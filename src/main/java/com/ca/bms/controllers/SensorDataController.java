package com.ca.bms.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ca.bms.entitys.SensorDataEntity;
import com.ca.bms.service.SensorDataService;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午10:48:16
 * @version:1.0
 */
@Controller
@RequestMapping(value="/sensordata")
public class SensorDataController {
	
	private static Logger logger = Logger.getLogger(SensorDataController.class);
	
	@Autowired
	SensorDataService sensorDataService;
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object addSensorData(
			@RequestParam(value="temperature",required = true) double temperature,
			@RequestParam(value="humidity",required = true) double humidity,
			@RequestParam(value="co",required = true) double co,
			@RequestParam(value="smoke",required = true) double smoke,
			@RequestParam(value="serialNum",required = true) String serialNum) {
		SensorDataEntity sensorDataEntity = new SensorDataEntity();
		sensorDataEntity.setHumidity(humidity);
		sensorDataEntity.setTemperature(temperature);
		sensorDataEntity.setCo(co);
		sensorDataEntity.setSmoke(smoke);
		sensorDataEntity.setSerialNum(serialNum);
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		regMsg.append(sensorDataService.savaSensorData(sensorDataEntity).getDisplayName());
		regMsg.append("\"}");
		logger.info("Receive User Add Request! :" + sensorDataEntity.toString());
		return regMsg.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/realtime", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getRealtimeSensorData(
			@RequestParam(value="username",required = true) double smoke,
			@RequestParam(value="serialNum",required = true) String serialNum) {

		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
//		regMsg.append(sensorDataService.savaSensorData(sensorDataEntity).getDisplayName());
//		regMsg.append("\"}");
//		logger.info("反馈一条实时数据 :" + );
		return regMsg.toString();
	}
}
