package com.cxb.project_mgj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxb.project_mgj.exception.LogName_BeRegisteredException;
import com.cxb.project_mgj.exception.PasswordErrorException;
import com.cxb.project_mgj.exception.UserBeDisabledException;
import com.cxb.project_mgj.exception.User_NotExist;
import com.cxb.project_mgj.mapper.MyGoods;
import com.cxb.project_mgj.pojo.Cart;
import com.cxb.project_mgj.pojo.Goodssize;
import com.cxb.project_mgj.pojo.User;
import com.cxb.project_mgj.service.MgjService;

@RestController
@RequestMapping("/ImgController")
public class MgjController {
	@Autowired
	MyGoods myGoodsimage;
	
	@Autowired
	MgjService mgjService;

	

	
	/**
	 * 瀑布流查询
	 * @param pagenum 传入要看的页码查询
	 * @return	返回查看的异步瀑布流的图片
	 */
	@RequestMapping("getAllByPage")
	public Object getAllByPage(Integer pagenum) {
		Integer rowcount=20;
		List<Map> imgAndGoodsinfo = mgjService.getImgAndGoodsinfo(rowcount, pagenum);
		return imgAndGoodsinfo;
	}
	/**
	 * 模糊查询
	 * @param like  传入搜索的模糊查询
	 * @return	返回查询的结果
	 */
	@RequestMapping("getByLike")
	public List<String> getByLike(String like) {
		List<String> goodsinfoByLike = mgjService.getGoodsinfoByLike("%"+like+"%");
		return goodsinfoByLike;
	}
	@RequestMapping("getBysize")
	public List<Goodssize> size() {
		//码数
		List<Goodssize> size = mgjService.size();
		return size;
	}
	
	
	
	
	/**
	 * 
	 * @param gdid	传入查询的衣服的id
	 * @return	返回查询衣服的价钱等
	 */
	@RequestMapping("getAllByPrice")
	public Map getAllByPrice(Integer gdid) {
		
		Map allByPrice = myGoodsimage.getAllByPrice(gdid);
		return allByPrice;
		
	}
}
