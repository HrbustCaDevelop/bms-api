package com.ca.bms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ca.bms.api.annotation.AuthPass;
import com.ca.bms.api.service.SensorService;
import com.ca.bms.api.service.UserService;
import com.ca.bms.common.entitys.SensorEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;
import com.ca.bms.common.enumtype.SensorStatusEnum;
import com.ca.bms.common.enumtype.UserStatusEnum;

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
			@RequestParam(value = "sensortype", required = true) String sensortype,
			@RequestParam(value = "sensoraddr", required = true) String sensoraddr) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");

		if (serialnum.trim().equals("") || sensortype.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getValue());
			regMsg.append("\"}");
			return regMsg.toString();
		}

		SensorEntity se = new SensorEntity();
		se.setSerialNum(serialnum);
		se.setSensorType(sensortype);
		se.setSensorAddr(sensoraddr);
		if (userService.checkAuth(username)) {
			try {
				regMsg.append(sensorService.saveSensorByObject(se)
						.getDisplayName());
			} catch (Exception e) {
				regMsg.append(SensorStatusEnum.SF.getValue());
			}
		} else {
			regMsg.append(UserStatusEnum.AF.getValue());
		}
		regMsg.append("\"}");
		return regMsg.toString();
	}
}