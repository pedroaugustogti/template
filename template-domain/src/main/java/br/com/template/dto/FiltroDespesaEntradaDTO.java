package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.TipoDespesa;

public class FiltroDespesaEntradaDTO {

	@EntityProperty("despesa.tipoDespesa")
	private TipoDespesa tipoDespesa;
	
	@EntityProperty("despesa.descricao")
	private String descricao;
	
	@EntityProperty("despesa.id")
	private Long despesaId;
	
	@EntityProperty("preco")
	private String preco;
	
	@EntityProperty("data")
	private String data;
	
	private List<SelectItem> tipoDespesas;
	
	public FiltroDespesaEntradaDTO(){
		tipoDespesas = TipoDespesa.selectItems();
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

	public Long getDespesaId() {
		return despesaId;
	}

	public void setDespesaId(Long despesaId) {
		this.despesaId = despesaId;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public List<SelectItem> getTipoDespesas() {
		return tipoDespesas;
	}
}