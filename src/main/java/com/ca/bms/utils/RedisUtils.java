package com.ca.bms.utils;

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
		pool = new JedisPool(config, "222.27.196.5", 12221);
	}

	/**
	 * 获取一个Jedis事例，顺手认证一下
	 * 
	 * @return
	 */
	private static Jedis newInstance() {
		Jedis jedis = pool.getResource();
		jedis.auth("Zj4xyBkgjd.");
		return jedis;
	}

	/**
	 * 删除缓存中的某个数据
	 * 
	 * @param key
	 */
	public static void remove(String key) {
		Jedis jedis = newInstance();
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
		Jedis jedis = newInstance();
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
		Jedis jedis = newInstance();
		try {
			jedis.set(key, value);
		} finally {
			pool.returnResource(jedis);
		}
	}
}
