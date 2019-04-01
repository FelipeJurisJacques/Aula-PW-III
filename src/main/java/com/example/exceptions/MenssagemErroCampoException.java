package com.example.exceptions;

import java.io.Serializable;

public class MenssagemErroCampoException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String campo;
	private String msg;
	
	public MenssagemErroCampoException() {
		super();
	}
	
	public MenssagemErroCampoException(String campo, String msg) {
		super();
		this.campo = campo;
		this.msg = msg;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
