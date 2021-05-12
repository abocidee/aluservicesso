package com.alu.itoken.service.sso.service.consumer.fallback;

import org.springframework.stereotype.Component;


import com.alu.itoken.service.sso.service.consumer.RedisService;

@Component
public class RedisServiceFallback implements RedisService {

	@Override
	public String put(String key, String value, long time) {
		// TODO Auto-generated method stub
      // return  Fallback.badGateway();
       return  null;
	}

	@Override
	public String getInfo(String key) {
		// TODO Auto-generated method stub
	//	return  Fallback.badGateway();
		return null;
	}
	@Override
	public String putB(String className, String key, String value) {
		return "request error";
	}

	@Override
	public Object getB(String className, String key) {
		return "request error";
	}

}
