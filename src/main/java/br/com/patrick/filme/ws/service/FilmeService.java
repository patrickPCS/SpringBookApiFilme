package br.com.patrick.filme.ws.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.patrick.filme.ws.exception.ResourceNotFoundException;
import br.com.patrick.filme.ws.model.Filme;
import br.com.patrick.filme.ws.respositorio.FilmeRespository;
import br.com.patrick.filme.ws.respositorio.ProdutoraRepository;


@Service
public class FilmeService {
	
	@Autowired
	FilmeRespository filmeRespository;
	
	@Autowired
	ProdutoraRepository produtoraRespository;
	//Negocios
	public Filme cadastrar(Filme filme, Integer produtoraId) {
		return produtoraRespository.findById(produtoraId).map( produtora -> {
			filme.setProdutora(produtora);
			return filmeRespository.save(filme); 
		}).orElseThrow(() -> new ResourceNotFoundException("ProdutoraId" + produtoraId + "not Found" ));
		
		//return filmeRespository.save(filme);
	}
	public Collection<Filme> buscartodos(Integer produtoraId){
		return filmeRespository.findByProdutoraId(produtoraId);
	}
	
	public Collection<Filme> buscarPorProdutora(Integer produtora_id){
		return filmeRespository.findByProdutoraId(produtora_id);
	}
	
	public Collection<Filme> buscarTesteTodos(){
		return filmeRespository.findAll();
	}
	
	public void excluir(Filme filme ) {
		filmeRespository.delete(filme);
	}
	
	public Optional<Filme> buscarPorId(Integer filme_id) {
	
		return filmeRespository.findById(filme_id);
		
	}
	
	
	public Filme alterar(Filme filmerequeste, Integer produtoraId, Integer filmeId) {
		if (!produtoraRespository.existsById(produtoraId)) {
			throw new ResourceNotFoundException("ProdutoraId " + produtoraId + " not found");
		}
		return filmeRespository.findById(filmeId).map( filme-> {
			filme.setNome(filmerequeste.getNome());
			filme.setDuracao(filmerequeste.getDuracao());
			filme.setAno(filmerequeste.getAno());
			return filmeRespository.save(filme);
		}).orElseThrow( () -> new ResourceNotFoundException("FilmeId" + filmeId + "not Found") );
		//return filmeRespository.save(filme);
	}
	public ResponseEntity<Object> excluir(Integer id, Integer produtora_id, Integer filme_id) {
		return filmeRespository.findByIdAndProdutoraId(filme_id, produtora_id).map( filme -> {
			filmeRespository.delete(filme);
			return ResponseEntity.ok().build();
		}).orElseThrow( () -> new ResourceNotFoundException("Filme Not Found with id" + filme_id + "and ProdutoraId" + produtora_id));
		
	}
	

}
