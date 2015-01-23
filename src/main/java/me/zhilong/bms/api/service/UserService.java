package me.zhilong.bms.api.service;

import java.util.List;
import java.util.Map;

import me.zhilong.bms.common.entitys.SensorEntity;
import me.zhilong.bms.common.entitys.UserEntity;
import me.zhilong.bms.common.enumtype.SensorStatusEnum;
import me.zhilong.bms.common.enumtype.UserStatusEnum;

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
	Map<String, Object> userLogin(UserEntity user);
	
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
	
	/**
	 * 获取用户的所有传感器
	 * @param username
	 * @return
	*/
	List<SensorEntity> getSensorByUsername(String username);
	
	/**
	 * 注册传感器到用户
	 * @param username
	 * @param serialnum
	 * @return
	*/
	SensorStatusEnum regsensor(String username, String serialnum);
	
	/**
	 * 权限检查
	 * @param username
	 * @return
	*/
	boolean checkAuth(String username);
}
