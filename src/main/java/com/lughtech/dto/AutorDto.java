package com.lughtech.dto;

import java.io.Serializable;

import com.lughtech.dominio.Usuario;

public class AutorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;

	public AutorDto() {
	}

	public AutorDto(Usuario autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
