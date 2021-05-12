package com.alu.itoken.service.sso.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alu.itoken.service.sso.service.consumer.fallback.RedisServiceFallback;

@FeignClient(value="itoken-service-redis",fallback = RedisServiceFallback.class)
public interface RedisService {
    
	@RequestMapping(value ="put" ,method = RequestMethod.POST)
	public String put(@RequestParam( "key")String key, @RequestParam("value") String value,@RequestParam("time") long time);
	
	
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public String getInfo(@RequestParam("key") String key);

	@RequestMapping(value = "boundHashPut",method = RequestMethod.POST)
	public String putB(@RequestParam("className") String className,@RequestParam("key") String key,@RequestParam("value") String value);

	@RequestMapping(value = "boundHashGet",method = RequestMethod.GET)
	public Object getB(@RequestParam("className") String className,@RequestParam("key") String key);
}
