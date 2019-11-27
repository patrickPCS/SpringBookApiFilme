package br.com.patrick.filme.ws.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.patrick.filme.ws.model.Filme;
import br.com.patrick.filme.ws.service.FilmeService;
import br.com.patrick.filme.ws.service.ProdutoraService;

@RestController
public class FilmesController {
	
	@Autowired
	FilmeService filmeService;
	
	@Autowired
	ProdutoraService produtoraService;
	
	//End point
	
	@RequestMapping(method = RequestMethod.POST,
			value = "produtoras/{produtora_id}/filmes",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Filme> cadastrarFilmes(@PathVariable (value = "produtora_id") Integer produtora_id,
												 @RequestBody @Valid Filme filme) {
		
		Filme filmeCadastrado = filmeService.cadastrar(filme, produtora_id);
		return new ResponseEntity<>(filmeCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			value = "/filmes", 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Filme>> buscarTodosFilmes() {
		
		Collection<Filme> filmebuscados = filmeService.buscarTesteTodos();
		return new ResponseEntity<>(filmebuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "produtoras/{produtora_id}/filmes/{filme_id}")
	public ResponseEntity<Object> excluirFilmes(@PathVariable (value = "produtora_id") Integer produtora_id,
											@PathVariable ( value = "filme_id") Integer filme_id) {
	
		return filmeService.excluir(filme_id, produtora_id, filme_id );
		 
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/filmes/{filme_id}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Filme> BuscarFilmesByID(@PathVariable (value = "filme_id") Integer filme_id) {
		
		Optional<Filme> filmebuscado = filmeService.buscarPorId(filme_id);
		return new ResponseEntity<>(filmebuscado.get(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "produtoras/{produtora_id}/filmes/{filme_id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Filme> alterarFilmes(@PathVariable (value = "produtora_id") Integer produtora_id,
									@PathVariable (value = "filme_id") Integer filme_id,
									 @RequestBody @Valid Filme filme) {
		
		Filme filmeAlterado = filmeService.alterar(filme, produtora_id, filme_id );
		return new ResponseEntity<>(filmeAlterado, HttpStatus.OK);
	
	}
	
	

}
