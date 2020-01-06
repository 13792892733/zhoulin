package com.cxb.project_mgj.exception;

public class UserBeDisabledException extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.out.println("用户被禁用");
	}
	
	
}
