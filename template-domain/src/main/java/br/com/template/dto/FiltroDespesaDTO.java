package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.TipoDespesa;

public class FiltroDespesaDTO {

	@EntityProperty("tipoDespesa")
	private TipoDespesa tipoDespesa;
	
	@EntityProperty(value="descricao")
	private String descricao;
	
	@EntityProperty(value="descricao", pesquisaExata=true, ignoraCaseSensitive=true)
	private String descricaoExata;
	
	private List<SelectItem> tipoDespesas;
	
	public FiltroDespesaDTO(){
		tipoDespesas = TipoDespesa.selectItems();
	}

	public List<SelectItem> getTipoDespesas() {
		return tipoDespesas;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
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