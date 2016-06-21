package br.com.template.dto;

import java.io.Serializable;
import java.util.List;

public class CarrinhoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1110673825907544608L;

	private String codigo;
	
	private List<CardapioDTO> listPedidosCarrinho;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<CardapioDTO> getListPedidosCarrinho() {
		return listPedidosCarrinho;
	}

	public void setListPedidosCarrinho(List<CardapioDTO> listPedidosCarrinho) {
		this.listPedidosCarrinho = listPedidosCarrinho;
	}
}