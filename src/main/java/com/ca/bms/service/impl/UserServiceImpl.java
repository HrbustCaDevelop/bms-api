package com.ca.bms.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.controllers.UserController;
import com.ca.bms.dao.UserDao;
import com.ca.bms.entitys.UserEntity;
import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.service.UserService;
import com.ca.bms.utils.EncodeTools;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午9:09:50
 * @version:1.0
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserDao userDao;
	
	/**
	 * 用户注册
	 */
	public UserStatusEnum register(UserEntity user) {
		if (user == null) {
			return UserStatusEnum.PI;
		}
		try {
			if (user.getUsername().trim().equals("") || user.getUsername() == null ||
					user.getPassword().trim().equals("") || user.getPassword() == null ||
					user.getNickname().trim().equals("") || user.getNickname() == null) {
					return UserStatusEnum.PI;
				}
		} catch (Exception e) {
			return UserStatusEnum.PI;
		}
		if (userDao.getUserByUsername(user.getUsername()) != null) {
			//用户名已存在
			return UserStatusEnum.AIE;
		}else {
			try {
				//对用户密码进行加密存储
				user.setPassword(EncodeTools.encoder(user.getPassword(),EncodeTools.giveMeSalt()));
				userDao.saveUserByUser(user);
				logger.info("One User Register Successful :" + user.toString());
				return UserStatusEnum.RS;
			} catch (Exception e) {
				return UserStatusEnum.RF;
			}
		}
	}
}
