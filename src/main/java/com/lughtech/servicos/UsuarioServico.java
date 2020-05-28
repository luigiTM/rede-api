package com.lughtech.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lughtech.dominio.Usuario;
import com.lughtech.dto.UsuarioDTO;
import com.lughtech.repositorio.UsuarioRepositorio;
import com.lughtech.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class UsuarioServico {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	public List<Usuario> buscarTodos() {
		return usuarioRepositorio.findAll();
	}

	public Usuario buscarPorId(String id) {
		Optional<Usuario> usuario = usuarioRepositorio.findById(id);
		return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}

	public Usuario inserir(Usuario usuario) {
		return usuarioRepositorio.insert(usuario);
	}

	public void deletar(String id) {
		buscarPorId(id);
		usuarioRepositorio.deleteById(id);
	}

	public Usuario deUmDTO(UsuarioDTO usuarioDto) {
		return new Usuario(usuarioDto.getId(), usuarioDto.getNome(), usuarioDto.getEmail());
	}

}
