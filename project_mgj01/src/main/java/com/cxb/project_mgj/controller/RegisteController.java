package com.cxb.project_mgj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxb.project_mgj.exception.LogName_BeRegisteredException;
import com.cxb.project_mgj.service.MgjService;

@RestController

public class RegisteController {

	
	@Autowired
	MgjService mgjService;
	/**
	 * 注册账户
	 * @param logname	输入注册账号
	 * @param password	输入注册密码
	 * @return	返回 1：注册成功， 2：已有此账号、3：验证码错误、4：两次密码输入不同
	 */
	@RequestMapping("/registe")
	public Object getRegiste(String logname,String password,String password_re,String code,HttpSession session) {
		Map map=new HashMap();
		
		//验证码校验
		String attribute = (String) session.getAttribute("rightCode");
		boolean equalsIgnoreCase = attribute.equalsIgnoreCase(code);
		
		if (!password.equals(password_re)) {
			map.put("msg", "4");
			return map;
		}
		if (!equalsIgnoreCase) {
			map.put("msg", "3");
			return map;
		}
		
		
		try {
			
			mgjService.registe(logname, password);
			map.put("msg", "1");
		} catch (LogName_BeRegisteredException e) {
			map.put("msg", "2");
		}
		
		return map;
	}
}
