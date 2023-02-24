package com.zifuji.cloud.base.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	private boolean success;
	private String message;
	private T result;
	private long timestamp = System.currentTimeMillis();

	public Result<T> setObj(T data) {
		this.code = 200;
		this.success = true;
		this.result = data;
		return this;
	}

	public Result<T> set200Mes(String mes) {
		this.code = 200;
		this.success = false;
		this.message = mes;
		return this;
	}

	public Result<T> set300Mes(String mes) {
		this.code = 300;
		this.success = false;
		this.message = mes;
		return this;
	}

	public Result<T> set400Mes(String mes) {
		this.code = 400;
		this.success = false;
		this.message = mes;
		return this;
	}

	public Result<T> set500Mes(String mes) {
		this.code = 500;
		this.success = false;
		this.message = mes;
		return this;
	}
}
