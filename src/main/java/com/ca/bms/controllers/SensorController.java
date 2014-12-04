package com.ca.bms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ca.bms.annotation.AuthPass;
import com.ca.bms.entitys.SensorEntity;
import com.ca.bms.enumtype.SensorDataStatusEnum;
import com.ca.bms.enumtype.SensorStatusEnum;
import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.service.SensorService;
import com.ca.bms.service.UserService;

/**
 * @author：刘志龙
 * @since：2014年12月1日 上午10:47:38
 * @version:1.0
 */
@Controller
@RequestMapping(value = "/sensor")
public class SensorController {

	@Autowired
	UserService userService;

	@Autowired
	SensorService sensorService;

	/**
	 * 后台注册传感器
	 * 
	 * @param username
	 * @param usertoken
	 * @param serialnum
	 * @param sensortype
	 * @param session
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getMySensor(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertoken", required = true) String usertoken,
			@RequestParam(value = "serialnum", required = true) String serialnum,
			@RequestParam(value = "sensortype", required = true) String sensortype) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");

		if (serialnum.trim().equals("") || sensortype.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getDisplayName());
			regMsg.append("\"}");
			return regMsg.toString();
		}

		SensorEntity se = new SensorEntity();
		se.setSerialNum(serialnum);
		se.setSensorType(sensortype);
		if (userService.checkAuth(username)) {
			try {
				regMsg.append(sensorService.saveSensorByObject(se)
						.getDisplayName());
			} catch (Exception e) {
				regMsg.append(SensorStatusEnum.SF.getDisplayName());
			}
		} else {
			regMsg.append(UserStatusEnum.AF.getDisplayName());
		}
		regMsg.append("\"}");
		return regMsg.toString();
	}
}
