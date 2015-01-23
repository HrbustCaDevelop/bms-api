package me.zhilong.bms.api.dao;

import me.zhilong.bms.common.entitys.UserEntity;

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
	int saveUserByUser(UserEntity user);
	/**
	 * 通过用户删除
	 * @param user
	*/
	void delUserByUser(UserEntity user);
	/**
	 * 通过用户更新信息
	 * @param user
	*/
	void updateUserByUser(UserEntity user);
	/**
	 * 通过UserId查找User
	 * @param userid
	 * @return
	*/
	UserEntity getUserById(Long userid);
	/**
	 * 通过User查找User
	 * @param user
	 * @return
	*/
	UserEntity getUserByUser(UserEntity user);
	
	/**
	 * 通过username查找user
	 * @param username
	 * @return
	*/
	UserEntity getUserByUsername(String username);
}
