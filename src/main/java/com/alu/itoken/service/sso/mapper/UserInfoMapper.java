package com.alu.itoken.service.sso.mapper;

import java.util.List;

import com.alu.itoken.service.sso.entity.User;
import org.apache.ibatis.annotations.Param;

import com.alu.itoken.service.sso.entity.UPermission;
import com.alu.itoken.service.sso.entity.UUserRole;
import com.alu.itoken.service.sso.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper<User> {
	
	List<UUserRole> findRoleByUid(@Param("uid") String uid); 
	
	List<UPermission> findPermissionByUid(@Param("uid") String uid);

	String login(@Param("username") String username,@Param("md5HashPass") String md5HashPass);

	String loginWithPhone(@Param("username") String username,@Param("md5HashPass") String md5HashPass);

	User getUserByEmail(String username);

	User getUserByPhone(String username);
}