package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.CategoriaMenu;
import br.com.template.domain.Situacao;

public class FiltroCardapioDTO {

	@EntityProperty("categoriaMenu")
	private CategoriaMenu categoria;
	
	@EntityProperty("descricao")
	private String descricaoPrato;
	
	@EntityProperty("situacao")
	private Situacao situacao;
	
	@EntityProperty(value="descricao", pesquisaExata=true, ignoraCaseSensitive=true)
	private String descricaoExata;
	
	private List<SelectItem> categoriaMenu;
	
	public FiltroCardapioDTO(){
		categoriaMenu = CategoriaMenu.selectItems();
	}

	public CategoriaMenu getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaMenu categoria) {
		this.categoria = categoria;
	}

	public String getDescricaoPrato() {
		return descricaoPrato;
	}

	public void setDescricaoPrato(String descricaoPrato) {
		this.descricaoPrato = descricaoPrato;
	}

	public List<SelectItem> getCategoriaMenu() {
		return categoriaMenu;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getDescricaoExata() {
		return descricaoExata;
	}

	public void setDescricaoExata(String descricaoExata) {
		this.descricaoExata = descricaoExata;
	}
}