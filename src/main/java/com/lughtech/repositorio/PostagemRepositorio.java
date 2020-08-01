package com.lughtech.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lughtech.dominio.Postagem;

@Repository
public interface PostagemRepositorio extends MongoRepository<Postagem, String> {

	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
	List<Postagem> buscarPorTitulo(String titulo);

	List<Postagem> findByTituloContaining(String titulo);

	@Query(" { $and: [ { data: {$gte: ?1} }, { data: { $lte: ?2 } }, { $or: [ { 'titulo': { $regex: ?0, $options: 'i' }, { 'corpo': { $regex: ?0, $options: 'i' }, { 'comentarios.texto': { $regex: ?0, $options: 'i' } ] } ] } ")
	List<Postagem> buscaCompleta(String texto, Date dataInicio, Date dataFim);
}
