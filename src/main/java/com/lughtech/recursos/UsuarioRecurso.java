package com.lughtech.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.dominio.Postagem;
import com.lughtech.dominio.Usuario;
import com.lughtech.dto.UsuarioDTO;
import com.lughtech.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso {

	@Autowired
	private UsuarioServico usuarioServico;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		List<Usuario> usuarios = usuarioServico.buscarTodos();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuariosDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable String id) {
		Usuario usuario = usuarioServico.buscarPorId(id);
		return ResponseEntity.ok().body(new UsuarioDTO(usuario));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioServico.deUmDTO(usuarioDto);
		usuario = usuarioServico.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody UsuarioDTO usuarioDto, @PathVariable String id) {
		Usuario usuario = usuarioServico.deUmDTO(usuarioDto);
		usuario.setId(id);
		usuario = usuarioServico.atualizar(usuario);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPorId(@PathVariable String id) {
		usuarioServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}/postagens", method = RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarPostagensPorIdUsuario(@PathVariable String id) {
		Usuario usuario = usuarioServico.buscarPorId(id);
		return ResponseEntity.ok().body(usuario.getPostagens());
	}

}
