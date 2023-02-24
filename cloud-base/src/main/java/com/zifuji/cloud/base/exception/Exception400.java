package com.zifuji.cloud.base.exception;

public class Exception400 extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 登陆状态校验异常
	public Exception400(String string) {
		super(string);
	}
}
