package me.zhilong.bms.api.utils;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @author：刘志龙
 * @since：2015年1月23日 下午9:21:22
 * @version:1.0
 */
public class MemcachedUtils implements CacheTools {
	@Value("#{configLoader['memcached.ip']}")
	private String ip;
	@Value("#{configLoader['memcached.port']}")
	private String port;
	@Value("#{configLoader['memcached.initConn']}")
	private int initConn;
	@Value("#{configLoader['memcached.minConn']}")
	private int minConn;
	@Value("#{configLoader['memcached.maxConn']}")
	private int maxConn;
	@Value("#{configLoader['memcached.maxIdle']}")
	private int maxIdle;
	@Value("#{configLoader['memcached.maintSleep']}")
	private int maintSleep;
	@Value("#{configLoader['memcached.socketTO']}")
	private int socketTO;
	@Value("#{configLoader['memcached.socketConnectTO']}")
	private int socketConnectTO;
	
	private MemCachedClient client;
	
	public void init() {
		client = new MemCachedClient();
		String[] addr = { ip + ":" + port };
		Integer[] weights = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(initConn);
		pool.setMinConn(minConn);
		pool.setMaxConn(maxConn);
		pool.setMaxIdle(maxIdle);
		pool.setMaintSleep(maintSleep);
		pool.setSocketTO(socketTO);
		pool.setSocketConnectTO(socketConnectTO);
		pool.setNagle(false);
		pool.initialize();
	}

	@Override
	public void remove(String key) {
		client.delete(key);  
	}

	@Override
	public String get(String key) {
		return client.get(key).toString();
	}

	@Override
	public void put(String key, String value) {
		Calendar timeout = Calendar.getInstance();
		timeout.add(Calendar.MINUTE, 20);
		client.set(key, value, timeout.getTime()); 
	}

	@Override
	public void put(String key, String value, int sec) {
		Calendar timeout = Calendar.getInstance();
		timeout.add(Calendar.SECOND, sec);
		client.set(key, value, timeout.getTime()); 
	}

}
