package com.ca.bms.service;

import com.ca.bms.entitys.UserEntity;
import com.ca.bms.enumtype.UserStatusEnum;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午8:06:35
 * @version:1.0
 */
public interface UserService {

	/**
	 * 用户注册
	 * @param user
	 * @return
	*/
	UserStatusEnum register(UserEntity user);
}
