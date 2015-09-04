package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.TipoProduto;

public class FiltroProdutoDTO {

	@EntityProperty("tipoProduto")
	private TipoProduto tipoProduto;
	
	@EntityProperty(value="descricao")
	private String descricao;
	
	@EntityProperty(value="descricao", pesquisaExata=true, ignoraCaseSensitive=true)
	private String descricaoExata;
	
	private List<SelectItem> tipoProdutos;
	
	public FiltroProdutoDTO(){
		tipoProdutos = TipoProduto.selectItems();
	}

	public List<SelectItem> getTipoProdutos() {
		return tipoProdutos;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
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
}