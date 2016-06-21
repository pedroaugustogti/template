package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.TipoProduto;

public class FiltroProdutoDTO {

	@EntityProperty("tipoProduto")
	private TipoProduto tipoProduto;
	
	@EntityProperty(value="descricao", ignoraCaseSensitive=true)
	private String descricao;
	
	@EntityProperty(value="fornecedor.nome", ignoraCaseSensitive=true)
	private String nomeFornecedor;
	
	@EntityProperty(value="marca", ignoraCaseSensitive=true)
	private String marca;
	
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
}