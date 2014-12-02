package com.ca.bms.controllers;

import java.util.UUID;

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
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		regMsg.append(userService.userRegister(user).getDisplayName());
		regMsg.append("\",\"userdata\":");
		regMsg.append(JSON.toJSONString(user));
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
	public Object checkUsername(String username) {
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		regMsg.append(userService.checkUsername(username).getDisplayName());
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
	public Object login(UserEntity user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		logger.info("Receive User Login Request! :" + user.toString());
		switch (userService.userLogin(user)) {
		case PI:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		case LF:
			regMsg.append(UserStatusEnum.LF.getDisplayName());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		case LS:
			regMsg.append(UserStatusEnum.LS.getDisplayName());
			//登陆成功授予一个Token，防止重复登陆，用于以后请求鉴权
			String userToken = UUID.randomUUID().toString();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("usertoken", userToken);
			regMsg.append("\",\"usertoken\":\"" + userToken + "\"");
			break;
		default:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"usertoken\":\"null\"");
			break;
		}
		regMsg.append("}");
		return regMsg.toString();
	}
	
	/**
	 * 用户登出
	 * @param username
	 * @param userToken
	 * @param session
	*/
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object logout(@RequestParam(value="username",required = true) String username, @RequestParam(value="usertoken",required = true) String usertoken, HttpServletRequest request) {
		HttpSession session = request.getSession();
		StringBuilder regMsg = new StringBuilder("{\"returnmsg\":\"");
		Object jusername = session.getAttribute("username");
		Object jusertoken = session.getAttribute("usertoken");
		if (jusername == null || jusertoken == null) {
			regMsg.append(UserStatusEnum.UINI.getDisplayName());
		}else if (jusername.equals(username) && jusertoken.equals(usertoken)) {
			session.removeAttribute("username");
			session.removeAttribute("usertoken");
			regMsg.append(UserStatusEnum.UILO.getDisplayName());
		}else {
			regMsg.append(UserStatusEnum.PI.getDisplayName());
		}
		regMsg.append("\"}");
		logger.info("User Logout: " + username);
		return regMsg.toString();
	}
}
