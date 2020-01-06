package com.cxb.project_mgj.exception;

public class PasswordErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.out.println("密码不正确");
	}
	
	
}
