package com.cxb.project_mgj.exception;
/**
 * 表示用户名不存在
 * @author Administrator
 *
 */
public class User_NotExist extends Exception{

	@Override
	public void printStackTrace() {
		System.out.println("用户名不存在");
	}
	
}
