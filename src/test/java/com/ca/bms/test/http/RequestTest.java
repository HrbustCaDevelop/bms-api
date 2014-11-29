package com.ca.bms.test.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ca.bms.utils.HttpClientUtils;

/**
 * @author：刘志龙
 * @since：2014年11月28日 下午10:21:12
 * @version:1.0
 */
public class RequestTest {

    private String url;

    @Before
    public void beforeRequest() {
        url = "http://localhost:8080/bms";
    }

    @Test
    public void testAliasRuleFetch() throws Exception {
    	
    	url = url + "/user/add";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "旭神");
    	paramMap.put("password", "123");
    	paramMap.put("phoneNum", "13764567998");
    	paramMap.put("nickname", "逗比");
    	
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }


}
