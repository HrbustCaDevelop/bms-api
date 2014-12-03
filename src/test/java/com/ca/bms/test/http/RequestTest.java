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
    public void test1UserAddByPOST() throws Exception {
    	
    	url = url + "/user/add";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "user1");
    	paramMap.put("password", "pass1");
    	paramMap.put("phoneNum", "13764567998");
    	paramMap.put("nickname", "nick1");
    	
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }

    @Test
    public void test2CheckUsernameByPOST() throws Exception {
    	
    	url = url + "/user/checkusername";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "旭神");
    	paramMap.put("nickname", "逗比");
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }
    
    @Test
    public void test3CheckLoginByPOST() throws Exception {
    	
    	url = url + "/user/login";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "user1");
    	paramMap.put("password", "pass1");
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }
    
    @Test
    public void test4CheckLogoutByPOST() throws Exception {
    	
    	url = url + "/user/logout";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "user1");
    	paramMap.put("userToken", "42ffb7e5-c05c-4869-91ca-b650781cdd16");
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }
    
    @Test
    public void test5CheckLogoutByPOST() throws Exception {
    	
    	url = url + "/user/logout";
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "user1");
    	paramMap.put("userToken", "42ffb7e5-c05c-4869-91ca-b650781cdd16");
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }
}
