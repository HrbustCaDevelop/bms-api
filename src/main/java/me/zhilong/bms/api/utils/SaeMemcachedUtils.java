package me.zhilong.bms.api.utils;

import com.sina.sae.memcached.SaeMemcache;

/**
 * @author：刘志龙
 * @since：2015年1月26日 下午9:29:54
 * @version:1.0
 */
public class SaeMemcachedUtils implements CacheTools {
	
	private SaeMemcache mc;
	
	public void init() {
		mc = new SaeMemcache();
	}
	
	@Override
	public void remove(String key) {
		mc.init();
		mc.delete(key);
	}

	@Override
	public String get(String key) {
		mc.init();
		return (String)mc.get(key);
	}

	@Override
	public void put(String key, String value) {
		mc.init();
		mc.add(key, value, 20 * 60);
	}

	@Override
	public void put(String key, String value, int sec) {
		mc.init();
		mc.add(key, value, sec);
	}

}
