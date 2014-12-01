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
	UserStatusEnum userRegister(UserEntity user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	*/
	UserStatusEnum userLogin(UserEntity user);
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	*/
	UserStatusEnum checkUsername(String username);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	*/
	UserStatusEnum updateUserMsg(UserEntity user);
}
