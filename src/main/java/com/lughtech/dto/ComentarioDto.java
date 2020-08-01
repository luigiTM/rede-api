package com.lughtech.dto;

import java.io.Serializable;
import java.sql.Date;

public class ComentarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String text;
	private Date data;
	private AutorDto autor;

	public ComentarioDto(String text, Date data, AutorDto autor) {
		this.text = text;
		this.data = data;
		this.autor = autor;
	}

	public ComentarioDto() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}

}
