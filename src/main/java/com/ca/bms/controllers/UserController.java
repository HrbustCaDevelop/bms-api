package com.ca.bms.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ca.bms.entitys.UserEntity;
import com.ca.bms.service.UserService;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午7:00:39
 * @version:1.0
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class); 
	
	@Autowired
	UserService userService;
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object addUser(UserEntity user) {
		logger.info("Receive User Add Request! :" + user.toString());
		//拼接JSON，使用JSON返回用户添加的结果以及用户数据，用于验证用户添加是否成功
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		regMsg.append(userService.userRegister(user).getDisplayName());
		regMsg.append("\",\"UserData\":");
		regMsg.append(JSON.toJSONString(user));
		regMsg.append("}");
		return regMsg.toString();
	}
	
	/**
	 * 检查用户名是否存在（用于AJAX注册验证）
	 * @param username
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value="/checkusername",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object checkUsername(String username) {
		logger.info("Receive User CheckUsername Request! :" + username);
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		regMsg.append(userService.checkUsername(username).getDisplayName());
		regMsg.append("\",\"username\":");
		regMsg.append("\"" + username + "\"");
		regMsg.append("}");
		return regMsg.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object login(UserEntity user) {
		logger.info("Receive User Login Request! :" + user.toString());
		StringBuilder regMsg = new StringBuilder("{\"returnMsg\":\"");
		//regMsg.append(userService.register(user).getDisplayName());
		regMsg.append("\",\"UserData\":");
		regMsg.append(JSON.toJSONString(user));
		regMsg.append("}");
		return regMsg.toString();
	}
}
