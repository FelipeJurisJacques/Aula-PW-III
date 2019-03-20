package com.example.DTO;

import java.io.Serializable;

import com.example.demo.entity.CursoEntity;

public class CursoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String nivel;
	
	public CursoDTO() {}
	
	public CursoDTO(CursoEntity obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.nivel = obj.getNivel();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}
