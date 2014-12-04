package com.ca.bms.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
private static Logger logger = Logger.getLogger(SensorController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	SensorService sensorService;
	
	/**
	 * 后台注册传感器
	 * @param username
	 * @param usertoken
	 * @param serialnum
	 * @param sensortype
	 * @param session
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getMySensor(
			@RequestParam(value="username",required = true) String username, 
			@RequestParam(value="usertoken",required = true) String usertoken,
			@RequestParam(value="serialnum",required = true) String serialnum, 
			@RequestParam(value="sensortype",required = true) String sensortype, 
			HttpSession session) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		
		if (username.trim().equals("") ||
			serialnum.trim().equals("") ||
			usertoken.trim().equals("") ||
			sensortype.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getDisplayName());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		
		SensorEntity se = new SensorEntity();
		se.setSerialNum(serialnum);
		se.setSensorType(sensortype);
		
		Object jusername = session.getAttribute("username");
		Object jusertoken = session.getAttribute("usertoken");
		if (jusername == null || jusertoken == null) {
			regMsg.append(UserStatusEnum.UINI.getDisplayName());
		}else if (jusername.equals(username) && jusertoken.equals(usertoken)) {
			if (userService.checkAuth(username)) {
				try {
					sensorService.saveSensorByObject(se);
					regMsg.append(SensorStatusEnum.SS.getDisplayName());
					logger.info("后台添加一个传感器: " + se.toString());
				} catch (Exception e) {
					regMsg.append(SensorStatusEnum.SF.getDisplayName());
				}	
			}else {
				regMsg.append(UserStatusEnum.AF.getDisplayName());
			}
		}else {
			regMsg.append(UserStatusEnum.PI.getDisplayName());
		}
		regMsg.append("\"}");
		return regMsg.toString();
	}
}
