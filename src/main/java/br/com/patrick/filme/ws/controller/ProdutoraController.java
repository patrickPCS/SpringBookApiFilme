package br.com.patrick.filme.ws.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.patrick.filme.ws.model.Produtora;
import br.com.patrick.filme.ws.service.ProdutoraService;

@RestController
public class ProdutoraController {
	
	@Autowired
	ProdutoraService produtoraService;
	
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/produtoras",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produtora> cadastrarProdutoras(@RequestBody @Valid Produtora produtora){
		Produtora produtoraCadastrada = produtoraService.cadastrar(produtora);
		return new ResponseEntity<Produtora>(produtoraCadastrada, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/produtoras/{produtora_id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produtora> buscarProdutora(@PathVariable (value = "produtora_id") Integer produtora_id){
		Optional<Produtora> produtorasBuscadas = produtoraService.buscarPorId(produtora_id);
		return new ResponseEntity<>(produtorasBuscadas.get(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/produtoras",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Produtora>> buscarTodasProdutoras(){
		Collection<Produtora> produtorasBuscadas = produtoraService.buscarTodas();
		return new ResponseEntity<>(produtorasBuscadas, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/produtoras/{produtora_id}")
	public ResponseEntity<Produtora> excluir(@PathVariable (value = "produtora_id") Integer produtora_id){
		
		
		Optional<Produtora> produtoraEncotrada = produtoraService.buscarPorId(produtora_id);
		if( produtoraEncotrada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			produtoraService.excluirById(produtora_id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/produtoras/{produtora_id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produtora> alterar(@PathVariable (value = "produtora_id") Integer produtora_id,
														@RequestBody @Valid Produtora produtora){
		if (produtora_id != produtora.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Produtora> produtoraEncotrada = produtoraService.buscarPorId(produtora_id);
		if( produtoraEncotrada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Produtora produtoraAlterada = produtoraService.altera(produtora);
			return new ResponseEntity<Produtora>(produtoraAlterada, HttpStatus.OK);
		}
	}
}
