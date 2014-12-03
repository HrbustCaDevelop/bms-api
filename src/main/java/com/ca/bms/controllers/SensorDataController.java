package com.ca.bms.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ca.bms.entitys.SensorDataEntity;
import com.ca.bms.enumtype.SensorDataStatusEnum;
import com.ca.bms.enumtype.UserStatusEnum;
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
	
	/**
	 * 添加一条传感器数据
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
			@RequestParam(value="temperature",required = true) double temperature,
			@RequestParam(value="humidity",required = true) double humidity,
			@RequestParam(value="co",required = true) double co,
			@RequestParam(value="smoke",required = true) double smoke,
			@RequestParam(value="serialnum",required = true) String serialnum) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		if (serialnum.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI);
			regMsg.append("\"}");
			return regMsg.toString();
		}
		SensorDataEntity sensorDataEntity = new SensorDataEntity();
		sensorDataEntity.setHumidity(humidity);
		sensorDataEntity.setTemperature(temperature);
		sensorDataEntity.setCo(co);
		sensorDataEntity.setSmoke(smoke);
		sensorDataEntity.setSerialNum(serialnum);
		regMsg.append(sensorDataService.savaSensorData(sensorDataEntity).getDisplayName());
		regMsg.append("\"}");
		logger.info("Receive User Add Request! :" + sensorDataEntity.toString());
		return regMsg.toString();
	}
	
	/**
	 * 获取实时数据
	 * @param username
	 * @param serialnum
	 * @param usertoken
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "/realtime", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getRealtimeSensorData(
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="serialnum",required = true) String serialnum,
			@RequestParam(value="usertoken",required = true) String usertoken,
			HttpSession session) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		
		if (username.trim().equals("") ||
				serialnum.trim().equals("") ||
				usertoken.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI);
			regMsg.append("\"}");
			return regMsg.toString();
		}
		
		Object jusername = session.getAttribute("username");
		Object jusertoken = session.getAttribute("usertoken");
		
		if (jusername == null || jusertoken == null) {
			//session中无数据
			regMsg.append(UserStatusEnum.UINI.getDisplayName());
			regMsg.append("\"}");
			return regMsg.toString();
		}else if (jusername.equals(username) && jusertoken.equals(usertoken)) {
			//用户数据正常
			SensorDataEntity sde = null;
			try {
				sde = sensorDataService.getRealTimeDataBySerialNum(serialnum);
				if (sde == null) {
					regMsg.append(SensorDataStatusEnum.PI.getDisplayName());
					regMsg.append("\"}");
					return regMsg.toString();
				}else {
					regMsg.append(SensorDataStatusEnum.DFS.getDisplayName());
				}
			} catch (ParseException e) {
				regMsg.append(SensorDataStatusEnum.PI.getDisplayName());
				regMsg.append("\"}");
				return regMsg.toString();
			}
			regMsg.append("\",\"data\":");
			regMsg.append(JSON.toJSONString(sde));
			logger.info("返回一条实时数据! :" + sde.toString());
			regMsg.append("}");
			return regMsg.toString();
		}else {
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\"}");
			return regMsg.toString();
		}
	}
}
