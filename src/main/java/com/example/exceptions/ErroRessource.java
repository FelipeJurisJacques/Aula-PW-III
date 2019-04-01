package com.example.exceptions;

import java.io.Serializable;

public class ErroRessource implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer StatusHttp;
	private String mensagem;
	
	public ErroRessource(Integer statusHttp, String mensagem) {
		super();
		StatusHttp = statusHttp;
		this.mensagem = mensagem;
	}

	public Integer getStatusHttp() {
		return StatusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		StatusHttp = statusHttp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
