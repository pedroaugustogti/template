package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Medida;
import br.com.template.domain.TipoProduto;

public class FiltroEstoqueDTO {

	@EntityProperty("produto.tipoProduto")
	private TipoProduto tipoProduto;
	
	@EntityProperty("medida")
	private Medida medida;
	
	@EntityProperty("produto.descricao")
	private String descricao;
	
	@EntityProperty("produto.id")
	private Long produtoId;
	
	@EntityProperty("quantidade")
	private String quantidade;
	
	@EntityProperty("preco")
	private String preco;
	
	private List<SelectItem> medidas;
	private List<SelectItem> subMedidas;
	private List<SelectItem> tipoProdutos;
	
	public FiltroEstoqueDTO(){
		medidas = Medida.selectItems();
		tipoProdutos = TipoProduto.selectItems();
	}
	
	public void subMedida(Medida med) {
		
		subMedidas = Medida.selectSubMedidaItems(med);
	}
	
	public List<SelectItem> getSubMedidas() {
		return subMedidas;
	}

	public List<SelectItem> getMedidas() {
		return medidas;
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

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
}