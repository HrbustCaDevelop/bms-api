package com.ca.bms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ca.bms.entitys.User;
import com.ca.bms.service.UserService;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午7:00:39
 * @version:1.0
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public Object addUser(User user) {
		StringBuilder regMsg = new StringBuilder("{\"regStatus\":\"");
		regMsg.append(userService.register(user).getDisplayName());
		regMsg.append("\",\"UserData\":");
		regMsg.append(JSON.toJSONString(user));
		regMsg.append("}");
		return regMsg.toString();
	}
}
