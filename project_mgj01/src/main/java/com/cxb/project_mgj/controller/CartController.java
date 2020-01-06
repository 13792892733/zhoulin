package com.cxb.project_mgj.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxb.project_mgj.pojo.Cart;
import com.cxb.project_mgj.pojo.User;
import com.cxb.project_mgj.service.MgjService;

@RestController
public class CartController {
	
	@Autowired
	MgjService mgjService;
	
	/**
	 * 更改要买的数量
	 * @param ctid	传入商品id
	 * @param count	传入购买数量
	 */
	@RequestMapping("/updataCartByGdcount")
	public Object updataCartByGdcount(Integer ctid,Integer count) {
		mgjService.updataCartByGdcount(ctid, count);
		return count;
	}
	
	/**
	 * 获取登录的账号密码
	 * @param session
	 * @return
	 */
	@RequestMapping("/getcartinfoes")
	public Object getCartInformations(HttpSession session){
		
		User user=(User) session.getAttribute("user");
		
		return mgjService.getCartForUser(user);
		
	}
	
	
	
	/**
	 * 放入购物车
	 * @param cart
	 * @param session
	 * @return
	 */
	@RequestMapping("/putintocart")
	public Object doPutIntoCart(Cart cart,HttpSession session){

		
		User user=(User) session.getAttribute("user");
		
		cart.setUserid(user.getUserid());
		
		
		mgjService.putIntoCart(cart);
		
		return true;
	}
	
	
}
