package com.cxb.project_mgj.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;


/**
 * 验证码
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/KaptchaController")
public class KaptchaController {


	    /**
	     * 验证码工具
	     */
	    @Autowired
	    DefaultKaptcha defaultKaptcha;

	    @RequestMapping("defaultKaptcha")
	    @ResponseBody
	    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        byte[] captcha = null;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	            // 将生成的验证码保存在session中,createText就是图片的数字，在service中使用session.getAttribute("rightCode")
	            String createText = defaultKaptcha.createText();
	            request.getSession().setAttribute("rightCode", createText);
	            BufferedImage bi = defaultKaptcha.createImage(createText);
	            ImageIO.write(bi, "jpg", out);
	        } catch (Exception e) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	            return;
	        }

	        captcha = out.toByteArray();
	        response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg");
	        ServletOutputStream sout = response.getOutputStream();
	        sout.write(captcha);
	        sout.flush();
	        sout.close();
	    }

	    
	
}
