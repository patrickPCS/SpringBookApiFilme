package br.com.patrick.filme.ws.respositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.patrick.filme.ws.model.Produtora;


public interface ProdutoraRepository extends JpaRepository<Produtora, Integer>{
	

}
