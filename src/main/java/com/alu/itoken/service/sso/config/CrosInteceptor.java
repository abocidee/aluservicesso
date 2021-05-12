package com.alu.itoken.service.sso.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class CrosInteceptor extends HandlerInterceptorAdapter {

    @Value("${service.corsOrigin}")  //跨域自定义配置application.yml自己配置
    private String corsOrigin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        //后端响应基本配置项
        if (!StringUtils.isEmpty(origin) && corsOrigin.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        //设备默认值
        response.setContentType("application/json;charset=UTF-8");
        return true;
    }
}
