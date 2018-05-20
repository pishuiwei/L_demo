package com.common.utils;

import java.io.Serializable;

public class ResponseMsg<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/*0请求成功,1请求失败*/
	public static final Integer STATUS_SUCCESS = Integer.valueOf(0);
	public static final Integer STATUS_FAILED = Integer.valueOf(-1);	
	
	/*请求状态*/
	private Integer status = Integer.valueOf(0);
	
	private String msg = "";
	private T data;	
	
	public ResponseMsg() {
	}
	
	/*一般使用此构造方法*/
	public ResponseMsg(T data) {
		setData(data);
	}
	
	public ResponseMsg(Integer status, String msg) {
		setStatus(status);
		setMsg(msg);
	}	
	
	public ResponseMsg(T data, Integer status) {
		setData(data);
		setStatus(status);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getError() {
		return this.status;
	}

	public String getUrl() {
		return this.msg;
	}

	public void failed(String msg) {
		setStatus(STATUS_FAILED);
		setMsg(msg);
	}
	
}









