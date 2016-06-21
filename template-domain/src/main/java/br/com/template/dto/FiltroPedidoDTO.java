package br.com.template.dto;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.SituacaoPedido;

public class FiltroPedidoDTO {

	@EntityProperty("situacao")
	private SituacaoPedido situacaoPedido;
	
	@EntityProperty("comanda.id")
	private Long comandaId;

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}

	public Long getComandaId() {
		return comandaId;
	}

	public void setComandaId(Long comandaId) {
		this.comandaId = comandaId;
	}
}