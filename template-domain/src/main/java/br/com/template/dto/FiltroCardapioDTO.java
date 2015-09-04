package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Situacao;
import br.com.template.domain.TipoProduto;

public class FiltroCardapioDTO {

	@EntityProperty("tipoProduto")
	private TipoProduto tipoProduto;
	
	@EntityProperty("descricao")
	private String descricaoPrato;
	
	@EntityProperty("situacao")
	private Situacao situacao;
	
	@EntityProperty(value="descricao", pesquisaExata=true, ignoraCaseSensitive=true)
	private String descricaoExata;
	
	private List<SelectItem> tipoProdutos;
	
	public FiltroCardapioDTO(){
		tipoProdutos = TipoProduto.selectItems();
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getDescricaoPrato() {
		return descricaoPrato;
	}

	public void setDescricaoPrato(String descricaoPrato) {
		this.descricaoPrato = descricaoPrato;
	}

	public List<SelectItem> getTipoProdutos() {
		return tipoProdutos;
	}

	public void setTipoProdutos(List<SelectItem> tipoProdutos) {
		this.tipoProdutos = tipoProdutos;
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