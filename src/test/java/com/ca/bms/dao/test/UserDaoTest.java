package com.ca.bms.dao.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ca.bms.dao.UserDao;
import com.ca.bms.entitys.UserEntity;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午8:48:10
 * @version:1.0
 */
public class UserDaoTest {
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void testUserInsert() {
		UserEntity user = new UserEntity();
		user.setNickname("逗比");
		
	}
}
