package com.ca.bms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.bms.dao.SensorDao;
import com.ca.bms.dao.UserDao;
import com.ca.bms.entitys.SensorEntity;
import com.ca.bms.entitys.UserEntity;
import com.ca.bms.enumtype.SensorStatusEnum;
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

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	SensorDao sensorDao;

	public UserStatusEnum userRegister (UserEntity user) {
		if (user == null) {
			return UserStatusEnum.PI;
		}
		try {
			if (user.getUsername().trim().equals("") ||
				user.getPassword().trim().equals("")) {
					return UserStatusEnum.PI;
				}
		} catch (Exception e) {
			return UserStatusEnum.PI;
		}
		if (userDao.getUserByUsername(user.getUsername()) != null) {
			//用户名已存在
			user.setPassword(EncodeTools.encoder(user.getPassword(),EncodeTools.giveMeSalt()));
			return UserStatusEnum.AIE;
		}else {
			try {
				//对用户密码进行加密存储
				user.setPassword(EncodeTools.encoder(user.getPassword(),EncodeTools.giveMeSalt()));
				if (userDao.saveUserByUser(user) > 0) {
					logger.info("One User Register Successful :" + user.toString());
					return UserStatusEnum.RS;
				} else {
					return UserStatusEnum.RF;
				}
			} catch (Exception e) {
				return UserStatusEnum.RF;
			}
		}
	}

	public Map<String, Object> userLogin(UserEntity user) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (user == null) {
			returnMap.put("status", UserStatusEnum.PI);
		}
		try {
			if (user.getUsername().trim().equals("") || user.getUsername() == null ||
					user.getPassword().trim().equals("") || user.getPassword() == null) {
				returnMap.put("status", UserStatusEnum.PI);
				}
		} catch (Exception e) {
			returnMap.put("status", UserStatusEnum.PI);
		}
		UserEntity temp = userDao.getUserByUsername(user.getUsername());
		if (temp != null) {
			//该用户存在，对用户密码进行再加密对比
			if (temp.getPassword().equals(EncodeTools.encoder(user.getPassword(), temp.getPassword().substring(0,4)))) {
				returnMap.put("status", UserStatusEnum.LS);
				returnMap.put("userdata", temp);
			}else {
				returnMap.put("status", UserStatusEnum.LF);
			}
		} else {
			returnMap.put("status", UserStatusEnum.LF);
		}
		
		return returnMap;
	}

	public UserStatusEnum checkUsername(String username) {
		if (username.trim().equals("") || null == username) {
			return UserStatusEnum.PI;
		}
		if (userDao.getUserByUsername(username) != null) {
			//用户名已存在
			return UserStatusEnum.AIE;
		} else {
			return UserStatusEnum.ACBU;
		}
	}

	@Override
	public List<SensorEntity> getSensorByUsername(String username) {
		return userDao.getUserByUsername(username).getSensorList();
	}
	
	@Override
	public SensorStatusEnum regsensor(String username, String serialnum) {
		SensorEntity se = sensorDao.getSensorBySerialNum(serialnum);
		if (se != null) {
			if (se.getUserId() == null) {
				se.setUserId(userDao.getUserByUsername(username).getId());
				try {
					sensorDao.updateSensorByObject(se);
					return SensorStatusEnum.RS;
				} catch (Exception e) {
					return SensorStatusEnum.RF;
				}
			}else {
				return SensorStatusEnum.SIR;
			}
		}else {
			return SensorStatusEnum.SINE;
		}
	}
	
	@Override
	public UserStatusEnum updateUserMsg(UserEntity user) {
		try {
			user.setPassword(EncodeTools.encoder(user.getPassword(), EncodeTools.giveMeSalt()));
			userDao.updateUserByUser(user);
			return UserStatusEnum.US;
		} catch (Exception e) {
			return UserStatusEnum.UF;
		}
	}

	@Override
	public boolean checkAuth(String username) {
		UserEntity ue = userDao.getUserByUsername(username);
		if (ue != null) {
			if (ue.getAuthNum() == 1) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
