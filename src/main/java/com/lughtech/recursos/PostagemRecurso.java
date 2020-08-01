package com.lughtech.recursos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lughtech.dominio.Postagem;
import com.lughtech.recursos.util.URL;
import com.lughtech.servicos.PostagemServico;

@RestController
@RequestMapping(value = "/postagens")
public class PostagemRecurso {

	@Autowired
	private PostagemServico postagemServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> buscarPorId(@PathVariable String id) {
		Postagem postagem = postagemServico.buscarPorId(id);
		return ResponseEntity.ok().body(postagem);
	}

	@RequestMapping(value = "/buscaTitulo", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarPorTitulo(@RequestParam(value = "texto", defaultValue = "") String texto) {
		texto = URL.decodificarParametro(texto);
		List<Postagem> postagens = postagemServico.buscarPorTitulo(texto);
		return ResponseEntity.ok().body(postagens);
	}
	
	@RequestMapping(value = "/buscaCompleta", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarCompleta(@RequestParam(value = "texto", defaultValue = "") String texto, @RequestParam(value = "inicio", defaultValue = "") String inicio, @RequestParam(value = "fim", defaultValue = "") String fim) {
		texto = URL.decodificarParametro(texto);
		Date dataInicio = URL.converterData(inicio, new Date(0L));
		Date dataFim = URL.converterData(fim, new Date());
		List<Postagem> postagens = postagemServico.buscaCompleta(texto, dataInicio, dataFim);
		return ResponseEntity.ok().body(postagens);
	}

}
