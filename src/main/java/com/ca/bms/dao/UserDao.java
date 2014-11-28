package com.ca.bms.dao;

import com.ca.bms.entitys.User;

/**
 * 用户dao操作
 * @author：刘志龙
 * @since：2014年11月28日 下午6:08:22
 * @version:1.0
 */
public interface UserDao {
	/**
	 * 保存用户
	 * @param user
	*/
	int saveUserByUser(User user);
	/**
	 * 通过用户删除
	 * @param user
	*/
	void delUserByUser(User user);
	/**
	 * 通过用户更新信息
	 * @param user
	*/
	void updateUserByUser(User user);
	/**
	 * 通过UserId查找User
	 * @param userid
	 * @return
	*/
	User getUserById(int userid);
	/**
	 * 通过User查找User
	 * @param user
	 * @return
	*/
	User getUserByUser(User user);
	
	/**
	 * 通过username查找user
	 * @param username
	 * @return
	*/
	User getUserByUsername(String username);
}
