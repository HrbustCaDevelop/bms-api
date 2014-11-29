package com.ca.bms.test.dao;

import org.junit.Assert;
import org.junit.Test;

import com.ca.bms.entitys.UserEntity;
import com.ca.bms.test.utils.SuperTestDependency;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午8:48:10
 * @version:1.0
 */
public class UserDaoTester extends SuperTestDependency {

	@Test
	public void test1UserInsert() {
		UserEntity user = new UserEntity();
		user.setUsername("旭神");
		user.setPassword("123456");
		user.setNickname("逗比");
		userDao.saveUserByUser(user);
	}
	
	@Test
	public void test2UserFetch() {
		UserEntity user = userDao.getUserByUsername("旭神");
		Assert.assertEquals("逗比", user.getNickname());
	}

}
