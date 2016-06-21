package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Empresa;

public class FiltroDespesaDTO {

	@EntityProperty("empresa")
	private Empresa empresa;
	
	@EntityProperty(value="descricao")
	private String descricao;
	
	@EntityProperty(value="descricao", pesquisaExata=true, ignoraCaseSensitive=true)
	private String descricaoExata;
	
	private List<SelectItem> empresas;
	
	public FiltroDespesaDTO(){
		empresas = Empresa.selectItems();
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoExata() {
		return descricaoExata;
	}

	public void setDescricaoExata(String descricaoExata) {
		this.descricaoExata = descricaoExata;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<SelectItem> getEmpresas() {
		return empresas;
	}
}