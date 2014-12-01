package com.ca.bms.test.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ca.bms.entitys.SensorDataEntity;
import com.ca.bms.entitys.SensorEntity;
import com.ca.bms.test.utils.SuperTestDependency;

/**
 * @author：刘志龙
 * @since：2014年11月29日 下午5:13:30
 * @version:1.0
 */
public class SenserDaoTester extends SuperTestDependency {
	@Test
	public void test1SensorInsert() {
		SensorEntity sensorEntity = new SensorEntity();
		sensorEntity.setSensorType("电子摄像机");
		sensorEntity.setSerialNum(UUID.randomUUID().toString());
		sensorDao.saveSensorBySensor(sensorEntity);
	}
	
	@Test
	public void test2SensorFetch() {
		List<SensorEntity> seList = sensorDao.getSensorByUserId(17L);
		for (SensorEntity sensorEntity : seList) {
			System.out.println(sensorEntity.getSensorType());
		}
	}
	
	@Test
	public void getJSON() {
		SensorDataEntity sensorDataEntity = new SensorDataEntity();
		sensorDataEntity.setTemperature(25L);
		sensorDataEntity.setHumidity(12L);
		sensorDataEntity.setCo(10L);
		sensorDataEntity.setSmoke(10L);
		sensorDataEntity.setSerialNum("78esskrjrskjdhg");
		System.out.println(JSON.toJSONString(sensorDataEntity));
	}
}
