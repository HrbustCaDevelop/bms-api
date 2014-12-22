package com.ca.bms.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.ca.bms.api.service.UserService;
import com.ca.bms.api.utils.EncodeTools;
import com.ca.bms.api.utils.RedisUtils;
import com.ca.bms.common.entitys.SensorEntity;
import com.ca.bms.common.entitys.UserEntity;
import com.ca.bms.common.enumtype.SensorDataStatusEnum;
import com.ca.bms.common.enumtype.UserStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午7:00:39
 * @version:1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	private static SimplePropertyPreFilter userFilter = new SimplePropertyPreFilter(
			UserEntity.class, "username", "nickname", "phoneNum");
	private static SimplePropertyPreFilter sensorFilter = new SimplePropertyPreFilter(
			SensorEntity.class, "modifyTime", "sensorType", "serialNum",
			"sensorAddr");

	@Autowired
	UserService userService;

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object addUser(UserEntity user, String phonenum) {
		user.setPhoneNum(phonenum);
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		regMsg.append(userService.userRegister(user).getValue());
		regMsg.append("\",\"userdata\":");
		regMsg.append(JSON.toJSONString(user, userFilter));
		regMsg.append("}");
		logger.info("Receive User Add Request! :" + user.toString());
		return regMsg.toString();
	}

	/**
	 * 检查用户名是否存在（用于AJAX注册验证）
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkusername", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object checkUsername(
			@RequestParam(value = "username", required = true) String username) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		regMsg.append(userService.checkUsername(username).getValue());
		regMsg.append("\",\"username\":");
		regMsg.append("\"" + username + "\"");
		regMsg.append("}");
		logger.info("Receive User CheckUsername Request! :" + username);
		return regMsg.toString();
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object login(UserEntity user) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		logger.info("Receive User Login Request! :" + user.toString());
		Map<String, Object> returnMap = userService.userLogin(user);
		switch ((UserStatusEnum) returnMap.get("status")) {
		case PI:
			regMsg.append(UserStatusEnum.PI.getValue());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		case LF:
			regMsg.append(UserStatusEnum.LF.getValue());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		case LS:
			regMsg.append(UserStatusEnum.LS.getValue());
			// 登陆成功授予一个Token，防止重复登陆，用于以后请求鉴权
			String userToken = EncodeTools.MD5(UUID.randomUUID().toString());
			RedisUtils.put(user.getUsername(), userToken);
			regMsg.append("\",\"usertoken\":\"" + userToken + "\"");
			regMsg.append(",\"userdata\":"
					+ JSON.toJSONString((UserEntity) returnMap.get("userdata"),
							userFilter));
			break;
		default:
			regMsg.append(UserStatusEnum.PI.getValue());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		}
		regMsg.append("}");
		return regMsg.toString();
	}

	/**
	 * 用户登出
	 * 
	 * @param username
	 * @param userToken
	 * @param session
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object logout(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertoken", required = true) String usertoken) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		RedisUtils.remove(username);
		regMsg.append(UserStatusEnum.UILO.getValue());
		regMsg.append("\"}");
		logger.info("User Logout: " + username);
		return regMsg.toString();
	}

	/**
	 * 获取用户的传感器信息
	 * 
	 * @param username
	 * @param usertoken
	 * @param session
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/mysensor", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object getMySensor(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertoken", required = true) String usertoken) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		List<SensorEntity> tempList = userService.getSensorByUsername(username);
		if (tempList.isEmpty()) {
			regMsg.append(UserStatusEnum.NAS.getValue());
			regMsg.append("\"");
		} else {
			regMsg.append(UserStatusEnum.FS.getValue());
			regMsg.append("\",\"sensor\":");
			regMsg.append(JSON.toJSONString(tempList, sensorFilter));
		}
		regMsg.append("}");
		return regMsg.toString();
	}

	/**
	 * 注册传感器
	 * 
	 * @param username
	 * @param usertoken
	 * @param session
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/regsensor", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object regsensor(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertoken", required = true) String usertoken,
			@RequestParam(value = "serialnum", required = true) String serialnum) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");

		if (serialnum.trim().equals("")) {
			regMsg.append(SensorDataStatusEnum.PI.getDisplayName());
			regMsg.append("\"}");
			return regMsg.toString();
		}
		regMsg.append(userService.regsensor(username, serialnum)
				.getDisplayName());
		regMsg.append("\"}");
		return regMsg.toString();
	}

	/**
	 * 更新用户信息
	 * 
	 * @param username
	 * @param usertoken
	 * @param password
	 * @param nickname
	 * @param phoneNum
	 * @param session
	 * @return
	 */
	@AuthPass
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object update(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "usertoken", required = true) String usertoken,
			String password, String nickname, String phonenum) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		UserEntity user = new UserEntity();
		user.setNickname(nickname);
		user.setPassword(password);
		user.setPhoneNum(phonenum);
		try {
			regMsg.append(userService.updateUserMsg(user).getValue());
		} catch (Exception e) {
			regMsg.append(UserStatusEnum.UF.getValue());
		}
		regMsg.append("\"}");
		return regMsg.toString();
	}
}
