package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Cargo;
import br.com.template.domain.Cidade;
import br.com.template.domain.Estado;
import br.com.template.domain.Situacao;

public class FiltroFuncionarioDTO {

	@EntityProperty("pessoa.nome")
	private String nome;
	
	@EntityProperty("pessoa.cpf")
	private String cpf;
	
	@EntityProperty("cargo")
	private Cargo cargo;
	
	@EntityProperty("situacao")
	private Situacao situacao;
	
	private List<SelectItem> cargos;
	
	private List<SelectItem> cidades;
	
	private List<SelectItem> estados;
	
	public FiltroFuncionarioDTO(){
		cargos = Cargo.selectItems();
		cidades = Cidade.selectItems();
		estados = Estado.selectItems();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<SelectItem> getCargos() {
		return cargos;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public List<SelectItem> getEstados() {
		return estados;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}