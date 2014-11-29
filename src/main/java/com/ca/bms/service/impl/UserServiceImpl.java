package com.ca.bms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	UserDao userDao;
	
	public UserStatusEnum register(UserEntity user) {
		if (userDao.getUserByUsername(user.getUsername()) != null) {
			return UserStatusEnum.AIE;
		}else {
			try {
				user.setPassword(EncodeTools.encoder(user.getPassword()));
				userDao.saveUserByUser(user);
				return UserStatusEnum.RS;
			} catch (Exception e) {
				return UserStatusEnum.RF;
			}
		}
	}
}
