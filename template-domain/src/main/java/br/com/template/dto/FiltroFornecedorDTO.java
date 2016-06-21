package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Medida;
import br.com.template.domain.TipoProduto;

public class FiltroFornecedorDTO {

	@EntityProperty("nome")
	private String nome;
	
	private List<SelectItem> medidas;
	private List<SelectItem> tiposProduto;
	
	public FiltroFornecedorDTO(){
		medidas = Medida.selectItems();
		tiposProduto = TipoProduto.selectItems();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<SelectItem> getMedidas() {
		return medidas;
	}

	public List<SelectItem> getTiposProduto() {
		return tiposProduto;
	}
}