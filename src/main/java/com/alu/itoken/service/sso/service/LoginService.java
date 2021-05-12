package com.alu.itoken.service.sso.service;

import com.alu.itoken.service.sso.entity.User;
import com.alu.itoken.service.sso.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginService extends IService<User> {


    boolean loginWithEmail(String username, String md5HashPass);

    boolean loginWithPhone(String username, String md5HashPass);

    User getUser(String username);

    User getUserByEmail(String username);

    User getUserByPhone(String username);
	
}
