package com.example.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.example.demo.entity.ServidoresEntity;

public class ServidoresDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//Bean validation java 7
	
	@NotEmpty(message="Peenchimento Obrigatorio")
	@Length(min=1, max=100, message="O nome deve conter entre 1 e 3")
	private String nome;
	private String email;
	private String senha;
	
	public ServidoresDTO() {}
	
	public ServidoresDTO(ServidoresEntity obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
