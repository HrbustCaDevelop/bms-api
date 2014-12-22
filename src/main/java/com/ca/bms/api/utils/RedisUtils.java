package com.ca.bms.api.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author：刘志龙
 * @since：2014年12月13日 下午4:56:34
 * @version:1.1
 */
public class RedisUtils {
	private static JedisPool pool;
	static {
		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxIdle(200);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		pool = new JedisPool(config, "121.42.140.165", 12221, 2000, "Zj4xyBkgjd.");
	}

	/**
	 * 删除缓存中的某个数据
	 * 
	 * @param key
	 */
	public static void remove(String key) {
		Jedis jedis = pool.getResource();
		try {
			jedis.del(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 */
	public static String get(String key) {
		Jedis jedis = pool.getResource();
		try {
			return jedis.get(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/**
	 * 存储键值对到缓存
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key, String value) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, value);
		} finally {
			pool.returnResource(jedis);
		}
	}
}
