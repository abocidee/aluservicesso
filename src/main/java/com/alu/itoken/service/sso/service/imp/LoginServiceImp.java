package com.alu.itoken.service.sso.service.imp;
import com.alibaba.fastjson.JSON;
import com.alu.itoken.service.sso.entity.User;
import com.alu.itoken.service.sso.utils.MapperUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alu.itoken.service.sso.mapper.UserInfoMapper;
import com.alu.itoken.service.sso.service.LoginService;
import com.alu.itoken.service.sso.service.consumer.RedisService;

@Service
public class LoginServiceImp extends ServiceImpl<UserInfoMapper, User> implements LoginService {
	private static final Logger logger =LoggerFactory.getLogger(LoginServiceImp.class);
    
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	

//	public UserInfo login(String username, String password) {
//		UserInfo userInfo =null;
//		    //得到缓存对象
//		//    String json =redisService.getInfo(username);
//		    //没有缓存数据
//		    if(json==null) {
//		    	QueryWrapper queryWrapper = new QueryWrapper();
//		    	queryWrapper.eq("name", username);
//			     userInfo =   userInfoMapper.selectOne(queryWrapper);
//			     String pwd= DigestUtils.md5DigestAsHex(password.getBytes());
//
//			     if(userInfo!=null && pwd.equals(userInfo.getPassword())) {
//			    	 try {
//				//		redisService.put(username, MapperUtils.obj2json(userInfo), 60*60*24);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			    	 return userInfo;
//			     }else {
//			    	 return null;
//			     }
//		    }else {//有缓存数据
//		    	try {
//					return MapperUtils.json2pojo(json, UserInfo.class);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					logger.error("触发熔断"+e.getMessage());
//					e.printStackTrace();
//				}
//		    }
//
//
//	        return userInfo;
//	}

	@Override
	public boolean loginWithEmail(String username, String md5HashPass) {

		String login = userInfoMapper.login(username, md5HashPass);
		if(login!=null){
			User user = null;
			String json =redisService.getInfo(username);
			if(json==null) {
				user = getUserByEmail(username);
				try {
					redisService.put(username, JSON.toJSONString(user), 60*60*24);
					//redisService.putB(User.class.getName(),username,user.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean loginWithPhone(String username, String md5HashPass) {
		String login = userInfoMapper.loginWithPhone(username,md5HashPass);
		if(login!=null){
			User user = null;
			String json =redisService.getInfo(username);
			if(json==null) {
				user = getUserByPhone(username);
				try {
					redisService.put(username, JSON.toJSONString(user), 60*60*24);
					//redisService.putB(User.class.getName(),username,user.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		return null;
	}

	@Override
	public User getUserByEmail(String username) {
		return userInfoMapper.getUserByEmail(username);
	}

	@Override
	public User getUserByPhone(String username) {
		return userInfoMapper.getUserByPhone(username);
	}

	@Override
	public Boolean isEmailExist(String emailOrPhone) {
		return userInfoMapper.isEmailExist(emailOrPhone);
	}
}

