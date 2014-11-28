package com.ca.bms.service;

import org.springframework.stereotype.Service;

import com.ca.bms.entitys.User;
import com.ca.bms.enumtype.UserStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午8:06:35
 * @version:1.0
 */
@Service(value="userService")
public interface UserService {

	/**
	 * 用户注册
	 * @param user
	 * @return
	*/
	UserStatusEnum register(User user);
}
