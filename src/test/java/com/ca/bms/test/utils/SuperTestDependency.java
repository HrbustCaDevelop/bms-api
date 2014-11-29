package com.ca.bms.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.ca.bms.dao.SensorDao;
import com.ca.bms.dao.SensorDataDao;
import com.ca.bms.dao.UserDao;

/**
 * @author：刘志龙
 * @since：2014年11月29日 下午5:15:10
 * @version:1.0
 */
@ContextConfiguration(locations = { "/bms-spring/spring-common.xml" })
public class SuperTestDependency extends AbstractJUnit4SpringContextTests {
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public SensorDao sensorDao;
	
	@Autowired
	public SensorDataDao sensorDataDao;
}
