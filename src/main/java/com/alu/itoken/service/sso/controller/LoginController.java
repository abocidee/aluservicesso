package com.alu.itoken.service.sso.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alu.itoken.service.sso.config.MD5Util;
import com.alu.itoken.service.sso.entity.*;
import com.alu.itoken.service.sso.utils.NoUtil;
import com.alu.itoken.service.sso.utils.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alu.itoken.service.sso.service.LoginService;
import com.alu.itoken.service.sso.service.consumer.RedisService;
import com.alu.itoken.service.sso.utils.CookieUtils;
import com.alu.itoken.service.sso.utils.MapperUtils;


@Controller
//@CrossOrigin(origins = "*" ,allowedHeaders = "*")
public class LoginController {
	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private RedisService redisService;

	@Autowired
	private LoginService loginService;
	@Autowired
	private JavaMailSender javaMailSender;

	private void validateCookieValue(String value) {
        int start = 0;
        int end = value.length();

        if (end > 1 && value.charAt(0) == '"' && value.charAt(end - 1) == '"') {
            start = 1;
            end--;
        }

        char[] chars = value.toCharArray();
        for (int i = start; i < end; i++) {
            char c = chars[i];
            if (c < 0x21 || c == 0x22 || c == 0x2c || c == 0x3b || c == 0x5c || c == 0x7f) {
                throw new IllegalArgumentException(
                        "rfc6265CookieProcessor.invalidCharInValue");
            }
        }
    }


	@RequestMapping(value = "/user/login",method = RequestMethod.POST)
	@ResponseBody
	public R loginvu(@RequestBody Map map ,HttpServletRequest request,HttpServletResponse response) {
		String username =(String)map.get("username");
		String password = (String)map.get("password");
		String s = MD5Util.md5(password);
		//String s = password.substring(2);
		boolean flag = false;
		User user =null;
		String token = UUID.randomUUID().toString();
		if (ValidateUtil.isMobile(username)) {//1 当用户名为phone时，用phone和密码验证
			flag =  loginService.loginWithPhone(username,s);
			String result =redisService.put(token , username, 60*60*24);
		}else {    //2 当用户名为邮箱时，用邮箱和密码验证
			flag =loginService.loginWithEmail(username,s);
			String result =redisService.put(token , username, 60*60*24);
		}
		return  flag==true?R.ok().data("token",token):R.error().message("用户或密码错误,请重新输入");
	}


	@RequestMapping(value = "/user/info",method = RequestMethod.GET)
	@ResponseBody
	public LoginBean loginvueinfo(
			//@RequestParam("token") String token
	) {
		DataBean dataBean = new DataBean();
		LoginBean loginBean = new LoginBean();
		List list = new ArrayList<>();
		list.add("admin");
		dataBean.setRoles(list);
		dataBean.setIntroduction("I am a super administrator");
		dataBean.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
		dataBean.setName("Super Admin");
		loginBean.setCode(20000);
		loginBean.setData(dataBean);
		return loginBean;
	}

	@RequestMapping(value = "/user/logout",method = RequestMethod.POST) 
	@ResponseBody
	public String loginoutString(
			) {
		//System.out.println(t);
		//return "{\"websocket\":true,\"origins\":[\"*:*\"],\"cookie_needed\":false,\"entropy\":2886321471}";
	   return "{\"code\":20000,\"data\":\"success\"}";
	}


	@PostMapping("/user/add")
	@ResponseBody
	public R addUser(@RequestBody Map<String,String> userMap,HttpServletRequest request) throws UnsupportedEncodingException {

		String name = userMap.get("name");
		String emailOrPhone = userMap.get("emailOrPhone");
		//判断邮箱是否存在
	    Boolean isEmailExist =	loginService.isEmailExist(emailOrPhone);
	    if(!(null==isEmailExist)) {
	    	return R.EnumResults(ResultCodeEnum.EMAIL_EXISTED);
		}
		String value = redisService.getInfo(emailOrPhone);
		String verifyCode = value.substring(1,value.length()-1);
		if(!verifyCode.equals(userMap.get("verifyCode"))){
			return R.EnumResults(ResultCodeEnum.VERIFY_CODE_ERROR);
		}

		MessageDigest md = null;
		StringBuilder sb = new StringBuilder(32);
		String passWord = userMap.get("password");
		String s = MD5Util.md5(passWord);
		User user = new User();
		user.setUserNo(NoUtil.generateUserNo());
		user.setPassword(s);
		user.setName(name);
		if(emailOrPhone.contains("@")&&emailOrPhone.contains(".com")){
			user.setEmail(emailOrPhone);
		}else {
			if(ValidateUtil.isMobile(emailOrPhone)) {
				user.setPhone(emailOrPhone);
			}
		}
		loginService.save(user);

		return R.ok().message("添加成功");
	}

    //发送验证码
	@PostMapping("/user/verifyCode")
	@ResponseBody
	public R sendverifyCode(@RequestBody Map<String,String> userMap,HttpServletRequest request){
		UUID uuid = UUID.randomUUID();
		String verifyCode = uuid.toString().substring(0, 6);
		String emailOrPhone = userMap.get("emailOrPhone");
		redisService.put(emailOrPhone,verifyCode,90);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); // 邮件发送者
		message.setTo(emailOrPhone); // 邮件接受者
		message.setSubject("派特蜜，请验证你的邮箱"); // 主题
		message.setText("你的验证码为："+ verifyCode); // 内容


		//发送邮件
		javaMailSender.send(message);
		return R.ok();

	}
}
