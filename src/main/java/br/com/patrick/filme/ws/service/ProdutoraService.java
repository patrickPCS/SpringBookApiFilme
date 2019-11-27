package br.com.patrick.filme.ws.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patrick.filme.ws.model.Produtora;
import br.com.patrick.filme.ws.respositorio.ProdutoraRepository;


@Service
public class ProdutoraService {
	
	@Autowired
	ProdutoraRepository produtoraRepository;
	
	public Produtora cadastrar(Produtora produtora) {
		return produtoraRepository.save(produtora);
	}
	
	public Collection<Produtora> buscarTodas(){
		return produtoraRepository.findAll();
	}
		
	public Optional<Produtora> buscarPorId(Integer produtora_ID) {
		return produtoraRepository.findById(produtora_ID);
	}
	
	public Produtora altera(Produtora produtora) {
		return produtoraRepository.save(produtora);
	}
	
	public void excluir(Produtora produtora) {
		produtoraRepository.delete(produtora);
	}
	
	public void excluirById(Integer produtora_Id) {
		produtoraRepository.deleteById(produtora_Id);
	}
	
	
	
}
