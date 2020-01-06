package com.cxb.project_mgj.test;

import java.util.List;
import java.util.Map;

import javax.websocket.Decoder.Text;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cxb.project_mgj.controller.KaptchaController;
import com.cxb.project_mgj.controller.MgjController;
import com.cxb.project_mgj.exception.LogName_BeRegisteredException;
import com.cxb.project_mgj.mapper.MyGoods;
import com.cxb.project_mgj.service.MgjService;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@SpringBootApplication(scanBasePackages="/com.cxb.project_mgj")
@MapperScan(value="/com.cxb.project_mgj.mapper")
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
	
	@Autowired
	MgjService mgjService;

	@Autowired
	KaptchaController kaptchaController;
	
	
	
	@Test
	public void test0() {
		try {
			mgjService.registe("tom", "6666");
			System.out.println("注册成功");
			
		} catch (LogName_BeRegisteredException e) {
			System.out.println("用户名已存在");
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
