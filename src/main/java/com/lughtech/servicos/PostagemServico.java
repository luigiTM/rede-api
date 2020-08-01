package com.lughtech.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lughtech.dominio.Postagem;
import com.lughtech.repositorio.PostagemRepositorio;
import com.lughtech.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class PostagemServico {

	@Autowired
	PostagemRepositorio postagemRepositorio;

	public Postagem buscarPorId(String id) {
		Optional<Postagem> postagem = postagemRepositorio.findById(id);
		return postagem.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}

	public List<Postagem> buscarPorTitulo(String titulo) {
		return postagemRepositorio.findByTituloContaining(titulo);
	}

	public List<Postagem> buscaCompleta(String texto, java.util.Date dataInicio, java.util.Date dataFim) {
		dataFim = new Date(dataFim.getTime() + 24 * 60 * 60 * 1000);
		return postagemRepositorio.buscaCompleta(texto, dataInicio, dataFim);
	}

}
