package com.ca.bms.controllers;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ca.bms.entitys.UserEntity;
import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.service.UserService;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午7:00:39
 * @version:1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

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
	public Object addUser(UserEntity user) {
		logger.info("Receive User Add Request! :" + user.toString());
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		StringBuilder regMsg = new StringBuilder("{\"ReturnMsg\":\"");
		regMsg.append(userService.userRegister(user).getDisplayName());
		regMsg.append("\",\"UserData\":");
		regMsg.append(JSON.toJSONString(user));
		regMsg.append("}");
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
	public Object checkUsername(String username) {
		logger.info("Receive User CheckUsername Request! :" + username);
		StringBuilder regMsg = new StringBuilder("{\"ReturnMsg\":\"");
		regMsg.append(userService.checkUsername(username).getDisplayName());
		regMsg.append("\",\"username\":");
		regMsg.append("\"" + username + "\"");
		regMsg.append("}");
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
	public Object login(UserEntity user, HttpSession session) {
		logger.info("Receive User Login Request! :" + user.toString());
		StringBuilder regMsg = new StringBuilder("{\"ReturnMsg\":\"");
		switch (userService.userLogin(user)) {
		case PI:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"UserToken\":\"null\"");
			break;
		case LF:
			regMsg.append(UserStatusEnum.LF.getDisplayName());
			regMsg.append("\",\"UserToken\":\"null\"");
			break;
		case LS:
			regMsg.append(UserStatusEnum.LS.getDisplayName());
			String userToken = UUID.randomUUID().toString();
			session.setAttribute(user.getUsername(), userToken);
			regMsg.append("\",\"UserToken\":\"" + userToken + "\"");
			break;
		default:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"UserToken\":\"null\"");
			break;
		}
		regMsg.append("}");
		return regMsg.toString();
	}
}
