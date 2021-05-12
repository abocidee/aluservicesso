package com.alu.itoken.service.sso.utils;

public enum HttpStatusContants {
	
	BAD_GETWAY(502,"从上游请求的服务器没有响应");
	private int status;
	private String message;
	
	
	private HttpStatusContants(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	
	

}
