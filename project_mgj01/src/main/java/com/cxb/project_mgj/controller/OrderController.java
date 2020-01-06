package com.cxb.project_mgj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxb.project_mgj.pojo.Orderinfo;
import com.cxb.project_mgj.pojo.Orderlist;
import com.cxb.project_mgj.pojo.User;
import com.cxb.project_mgj.service.MgjService;

@RestController
public class OrderController {

	@Autowired
	MgjService mgjService;
	
	@RequestMapping("/orderinfo")
	@Transactional
	public Object orderinfo(Orderinfo order , HttpSession session) {
		User user = (User) session.getAttribute("user");
		mgjService.setOrderinfo(order);
		order.setOfid(user.getUtid().toString());
		return true;
		
	}
	@RequestMapping("/orderlist")
	@Transactional	
	public Object orderlist(Orderlist order , HttpSession session) {
		mgjService.setOrderlist(order);
		String ofid = (String) session.getAttribute("user");
		order.setOfid(ofid);
		
		
		
		
		return true;
	}
}
