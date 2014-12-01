package com.ca.bms.controllers;

import java.util.UUID;

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
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		// 拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		regMsg.append(userService.userRegister(user).getDisplayName());
		regMsg.append("\",\"userData\":");
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
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
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
	public Object login(UserEntity user, HttpSession session) {
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		logger.info("Receive User Login Request! :" + user.toString());
		switch (userService.userLogin(user)) {
		case PI:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"userToken\":\"null\"");
			break;
		case LF:
			regMsg.append(UserStatusEnum.LF.getDisplayName());
			regMsg.append("\",\"userToken\":\"null\"");
			break;
		case LS:
			regMsg.append(UserStatusEnum.LS.getDisplayName());
			//登陆成功授予一个Token，防止重复登陆，用于以后请求鉴权
			String userToken = UUID.randomUUID().toString();
			session.setAttribute(user.getUsername(), userToken);
			regMsg.append("\",\"userToken\":\"" + userToken + "\"");
			break;
		default:
			regMsg.append(UserStatusEnum.PI.getDisplayName());
			regMsg.append("\",\"userToken\":\"null\"");
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
	public Object logout(@RequestParam(value="username",required = true) String username, @RequestParam(value="userToken",required = true) String userToken, HttpSession session) {
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		String tokenInS = session.getAttribute(username).toString();
		if (tokenInS.trim().equals("") || null == tokenInS) {
			regMsg.append(UserStatusEnum.UINI.getDisplayName());
		}else if (!tokenInS.equals(userToken)) {
			regMsg.append(UserStatusEnum.PI.getDisplayName());
		}else {
			session.removeAttribute(username);
			regMsg.append(UserStatusEnum.UILO.getDisplayName());
		}
		regMsg.append("\"}");
		logger.info("User Logout: " + username);
		return regMsg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object updateUserMsg(UserEntity user,  @RequestParam(value="userToken",required = true) String userToken, HttpSession session) {
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		if (null == user || user.getUsername().trim().equals("") || user.getUsername() == null) {
			regMsg.append(UserStatusEnum.PI.getDisplayName());
		}else if(!session.getAttribute(user.getUsername()).toString().equals(userToken)){
			regMsg.append(UserStatusEnum.UINI.getDisplayName());
		}else {
			
		}
		regMsg.append("\"}");
		logger.info("Receive User Update Message Request! :" + user.getUsername());
		return regMsg.toString();
	}
}
