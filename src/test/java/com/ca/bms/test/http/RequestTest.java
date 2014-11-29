package com.ca.bms.test.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
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
    private List<NameValuePair> paramList;

    @Before
    public void beforeRequest() {
        url = "http://localhost:8080/bms";
    }

//    @After
//    public void afterRequest() throws Exception {
//    	Map<String, String> paramMap = new HashMap<String, String>();
//    	HttpClientUtils.doPost(url, paramMap);
//    }

    @Test
    public void testAliasRuleFetch() throws Exception {
//        paramList = new ArrayList<NameValuePair>();
//        NameValuePair nameValuePair = new BasicNameValuePair("username", "旭神");
//        paramList.add(nameValuePair);
//
//        NameValuePair nameValuePair0 = new BasicNameValuePair("password", "123");
//        paramList.add(nameValuePair0);
//
//        NameValuePair nameValuePair1 = new BasicNameValuePair("phoneNum", "13764567998");
//        paramList.add(nameValuePair1);
//        
//        NameValuePair nameValuePair2 = new BasicNameValuePair("nickname", "逗比");
//        paramList.add(nameValuePair2);
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("username", "旭神");
    	paramMap.put("password", "123");
    	paramMap.put("phoneNum", "13764567998");
    	paramMap.put("nickname", "逗比");
    	
        url = url + "/user/add";
        System.out.println(HttpClientUtils.doPost(url, paramMap));
    }


}
