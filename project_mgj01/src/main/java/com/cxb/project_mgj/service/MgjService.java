package com.cxb.project_mgj.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxb.project_mgj.exception.LogName_BeRegisteredException;
import com.cxb.project_mgj.exception.PasswordErrorException;
import com.cxb.project_mgj.exception.UserBeDisabledException;
import com.cxb.project_mgj.exception.User_NotExist;
import com.cxb.project_mgj.mapper.CartMapper;
import com.cxb.project_mgj.mapper.GoodspriceMapper;
import com.cxb.project_mgj.mapper.GoodssizeMapper;
import com.cxb.project_mgj.mapper.MyCartMapper;
import com.cxb.project_mgj.mapper.MyGoods;
import com.cxb.project_mgj.mapper.OrderinfoMapper;
import com.cxb.project_mgj.mapper.OrderlistMapper;
import com.cxb.project_mgj.mapper.UserMapper;
import com.cxb.project_mgj.pojo.Cart;
import com.cxb.project_mgj.pojo.CartExample;
import com.cxb.project_mgj.pojo.Goodsprice;
import com.cxb.project_mgj.pojo.GoodspriceExample;
import com.cxb.project_mgj.pojo.Goodssize;
import com.cxb.project_mgj.pojo.GoodssizeExample;
import com.cxb.project_mgj.pojo.Orderinfo;
import com.cxb.project_mgj.pojo.Orderlist;
import com.cxb.project_mgj.pojo.User;
import com.cxb.project_mgj.pojo.UserExample;

@Service
public class MgjService {
	
	@Autowired
	MyGoods myGoods;
	
	@Autowired
	GoodssizeMapper goodssizeMapper;
	
	@Autowired
	UserMapper usermapper;
	
	@Autowired
	CartMapper cartMapper;
	
	
	@Autowired
	MyCartMapper mycartMapper;
	
	
	@Autowired
	OrderinfoMapper orderinfoMapper;
	
	@Autowired
	OrderlistMapper orderlistMapper;
	
	@Autowired
	GoodspriceMapper goodspriceMapper;
	
	
	@Autowired
	Orderinfo orderinfo;
	
	@Autowired
	Orderlist orderlist;
	
	@Transactional
	public void setOrderinfo(Orderinfo order) {
		
		orderinfo.setOfid(UUID.randomUUID().toString());
		orderinfo.setOfdate(new  Date());
		orderinfo.setOfstate(1);
		orderinfo.setAddress(order.getAddress());
		orderinfo.setContactnumber(order.getContactnumber());
		orderinfo.setRecipient(order.getRecipient());
		
	}
			
	@Transactional
	public void setOrderlist(Orderlist order) {
		
		orderlist.setOlid(UUID.randomUUID().toString());
		orderlist.setGdid(order.getGdid());
		orderlist.setGdcount(order.getGdcount());
		orderlist.setGsid(order.getGsid());
		orderlist.setPrice(order.getPrice());
		
	}
	
	
	@Transactional
	public String   mkOrderInformation(Orderinfo orderinfo,Integer[] ctids){
		
		StringBuffer buffer=new StringBuffer("of_");
		buffer.append(UUID.randomUUID().toString());
		
		orderinfo.setOfid(buffer.toString());
		orderinfo.setOfstate(1);
		orderinfo.setOfdate(new Date());
		
		orderinfoMapper.insert(orderinfo);
		
		
		CartExample example=new CartExample();
		List<Integer> values=Arrays.asList(ctids);
		
		example.createCriteria().andCtidIn(values);
		
		List<Cart> carts= cartMapper.selectByExample(example);
		
		
		
		for(int i=0;i<carts.size();i++){
			
			Cart ct=carts.get(i);
			
			Orderlist ol=new Orderlist();
			
			StringBuffer bf=new StringBuffer("OL_");
			bf.append(buffer.toString()).append("_").append((i+1));
			
			//设置订单明细编号
			ol.setOlid(bf.toString());
			
			//设置所属订单编号
			ol.setOfid(orderinfo.getOfid());
			
			//设置商品信息
			ol.setGdid(ct.getGdid());
			//设置商品购买数量
			ol.setGdcount(ct.getGdcount());
			
			//设置商品尺寸
			ol.setGsid(ct.getGsid());
			
			//根据 商品编号获取单品价格
			GoodspriceExample gex=new GoodspriceExample();
			gex.createCriteria().andGdidEqualTo(ct.getGdid()).andUtidEqualTo(1);
			List<Goodsprice> prices= goodspriceMapper.selectByExample(gex);
			//设置商品价格
			ol.setPrice(prices.get(0).getPrice());
			
			orderlistMapper.insert(ol);
			
			
			
		}
//		int a=1/0;
		
		
		return orderinfo.getOfid();
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 更改要买的数量
	 * @param ctid	传入商品id
	 * @param count	传入购买数量
	 */
	public  boolean updataCartByGdcount(Integer ctid,Integer count) {
		Cart cart = cartMapper.selectByPrimaryKey(ctid);
		if (cart!=null) {
			cart.setGdcount(count);
			
			
			return cartMapper.updateByPrimaryKey(cart)==1;
		}
		return false;
		
		
		
	}

	
	/**
	 *根据用户获取 购物车相关信息展示
	 * @param user  指定用户对象
	 * @return 封装有 编号、图片、尺寸文本、单价等信息的列表
	 */
	public List<Map> getCartForUser(User user){
		
		return mycartMapper.getCartInfoesByUserID(user.getUserid()) ;
		
	}
	
	/**
	 * 将指定商品放入购物车
	 * @param info 封装有购物车商品信息的对象
	 */
	@Transactional
	public void putIntoCart(Cart info){
		
		CartExample example=new CartExample();
		example.createCriteria().andUseridEqualTo(info.getUserid()).andGsidEqualTo(info.getGsid()).andGdidEqualTo(info.getGdid());
		List<Cart> carts= cartMapper.selectByExample(example);
		
		//查询 指定用户购物车表中是否已存在 相关规格商品
		//如果存在，改变 商品数量即可
		if(carts!=null&&carts.size()==1){
			
			Cart ct=carts.get(0);
			ct.setGdcount(ct.getGdcount()+info.getGdcount());
			
			cartMapper.updateByPrimaryKey(ct);
			
		}
		else{// 如果是新商品，添加购物车信息

			cartMapper.insert(info);
			
		}
		
		
		
		
		
	}
	
	
	
	/**
	 * 登录
	 * @param logname	用户名
	 * @param password	密码
	 * @throws UserBeDisabledException 		用户账号被禁用
	 * @throws User_NotExist 				用户名不存在
	 * @throws PasswordErrorException    密码不正确
	 */
	@Transactional
	public User log_into(String logname,String password) throws UserBeDisabledException, User_NotExist, PasswordErrorException{
		UserExample example = new UserExample();
		example.createCriteria().andLognameEqualTo(logname);
		List<User> users=usermapper.selectByExample(example);
		
		if (users==null||users.size()!=1) {
			throw new User_NotExist();
		}
		User user=users.get(0);
		if(user.getStatus()!=1){
			throw new UserBeDisabledException();
		}
		String salt = user.getSalt();
		
		Md5Hash md5=new Md5Hash(password,salt);
		
		 if(!user.getPassword().equals(md5.toString())){
			 throw new PasswordErrorException();
		 }
		 
		 return user;
		
	}
	
	
	
	
	/**
	 * 注册
	 * @param logname	用户名
	 * @param password	密码
	 * @throws LogName_BeRegisteredException	如果有重复的用户名抛出异常，中断注册
	 */
	@Transactional
	public void registe(String logname,String password) throws LogName_BeRegisteredException {
		
		UserExample example =new UserExample();
		
		//判断用户名是否被使用
		example.createCriteria().andLognameEqualTo(logname);
		Integer count = usermapper.countByExample(example);
		if (count==1) {
			throw new LogName_BeRegisteredException();
		}
		User user= new User();
		//封装user信息、加密
		String salt=UUID.randomUUID().toString();
		
		Md5Hash md5Hash = new  Md5Hash(logname,salt);
		
		user.setLogname(logname);
		user.setPassword(md5Hash.toString());
		user.setSalt(salt);
		user.setStatus(1);
		user.setUtid(1);
		usermapper.insert(user);
		
	}
	
	
	

	
	
	
	/**
	 * 根据指定页码获取商品信息的方法
	 * @param rowcount  每页加载信息数量
	 * @param pagenum  指定页码
	 * @return
	 */
	public List<Map> getImgAndGoodsinfo(Integer rowcount,Integer pagenum) {
		
		RowBounds rowBound =new RowBounds(((pagenum-1)*rowcount), rowcount);
		return myGoods.getAll(rowBound);
	}
	/**
	 * 模糊查询
	 * @param like 传入查询的关键字
	 * @return
	 */
	public List<String> getGoodsinfoByLike(String like) {
		List<String> byLike = myGoods.getByLike(like);
		return byLike;
	}
	/**
	 * 商品码数
	 * @return
	 */
	public List<Goodssize>  size() {
		GoodssizeExample example = new GoodssizeExample();
		List<Goodssize> size = goodssizeMapper.selectByExample(example);
		return size;
	}
	/**
	 * 查询商品价格
	 * @param id
	 * @return
	 */
	public Map getGoodsinfoPrice(Integer id) {
		Map allByPrice = myGoods.getAllByPrice(id);

		return allByPrice;
	}
	
	
	
	
	
	
	
	
	
}
