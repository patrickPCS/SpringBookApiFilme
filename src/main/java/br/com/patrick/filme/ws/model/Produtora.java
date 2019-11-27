package br.com.patrick.filme.ws.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "produtoras")
public class Produtora {
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotBlank( message ="{nome.not.blank}")
	private String nome;
	@NotBlank( message ="{dataCriacao.not.blank}")
	private String dataCriacao;
	
	@OneToMany(mappedBy = "produtora", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	private Set<Filme> filmes;


	public Set<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(Set<Filme> filmes) {
		this.filmes = filmes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
	
	

}
