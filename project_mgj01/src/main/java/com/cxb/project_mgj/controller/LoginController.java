package com.cxb.project_mgj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxb.project_mgj.exception.PasswordErrorException;
import com.cxb.project_mgj.exception.UserBeDisabledException;
import com.cxb.project_mgj.exception.User_NotExist;
import com.cxb.project_mgj.pojo.User;
import com.cxb.project_mgj.service.MgjService;

@RestController

public class LoginController {

	@Autowired
	MgjService mgjService;
	
	
	/**
	 * 
	 * 得到session 中用户登录的数据
	 * @param session
	 * @return
	 */
	@RequestMapping("/useronline")
	public Object userIsOnline(HttpSession session){
		
		User user=(User) session.getAttribute("user");
		System.out.println(session);
		return user;
	}
	
	/**
	 * 登录账户
	 * @param logname	输入账号
	 * @param password	输入密码
	 * @return	返回 1：登录成功， 2：账号不存在、3、密码错误 4：验证码错误、5、账号被冻结
	 */
	@RequestMapping("/log_into")
	public Object log_into(String logname,String password,String code,HttpSession session) {
		
		System.out.println(session);
		Map map=new HashMap();
		
		//验证码校验
		String attribute = (String) session.getAttribute("rightCode");
		boolean equalsIgnoreCase = attribute.equalsIgnoreCase(code);
		if (!equalsIgnoreCase) {
			map.put("msg", "4");
			return map;
		}
		
		try {
			User user = mgjService.log_into(logname, password);
			session.setAttribute("user", user);
			map.put("msg", 1);
		} catch (UserBeDisabledException e) {
			map.put("msg", 5);
		} catch (User_NotExist e) {
			map.put("msg", 2);
		} catch (PasswordErrorException e) {
			map.put("msg", 3);
		}
		
	
		
		
		return map;
	}
	
}
