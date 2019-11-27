package br.com.patrick.filme.ws.respositorio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.patrick.filme.ws.model.Filme;

@Repository
public interface FilmeRespository extends JpaRepository<Filme, Integer> {

	Collection<Filme> findByProdutoraId(Integer produtoraId);
	
	Optional<Filme> findByIdAndProdutoraId(Integer id, Integer produtoraId);

}
