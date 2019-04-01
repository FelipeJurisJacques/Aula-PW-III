package com.example.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroRessource{
	private static final long serialVersionUID = 1L;

	private List<MenssagemErroCampoException> listaErros = new ArrayList<>();
	
	public ErroValidacao(
			Integer httpStatus,
			String msg
	) {
		super(httpStatus, msg);
	}
	
	public List<MenssagemErroCampoException> getListaErros(){
		return listaErros;
	}
	
	public void setListaErros(
			List<MenssagemErroCampoException> listaErros
	) {
		this.listaErros = listaErros;
	}
	
	public void addErro(String campo, String msg) {
		MenssagemErroCampoException m = new MenssagemErroCampoException(campo, msg);
		this.listaErros.add(m);
	}
}
