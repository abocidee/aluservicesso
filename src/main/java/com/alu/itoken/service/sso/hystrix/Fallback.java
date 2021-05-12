package com.alu.itoken.service.sso.hystrix;


import com.alu.itoken.service.sso.entity.BaseResult;
import com.alu.itoken.service.sso.utils.HttpStatusContants;
import com.alu.itoken.service.sso.utils.MapperUtils;
import com.google.common.collect.Lists;

public class Fallback {
	/**
	 * 502 错误
	 * @return
	 */
  public static String badGateway() {
		 BaseResult baseresult =BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(String.valueOf(HttpStatusContants.BAD_GETWAY.getStatus()), HttpStatusContants.BAD_GETWAY.getMessage())));
		 
		  try {
			return MapperUtils.obj2json(baseresult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return null;
	
  }
}
