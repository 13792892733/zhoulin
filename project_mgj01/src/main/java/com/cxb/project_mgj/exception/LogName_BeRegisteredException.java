package com.cxb.project_mgj.exception;



/**
 * 表示用户名已经被使用
 * @author Administrator
 *
 */

public class LogName_BeRegisteredException  extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.out.println("用户名被使用");
	}
	
	
	
}
