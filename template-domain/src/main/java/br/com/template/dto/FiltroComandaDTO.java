package br.com.template.dto;

import br.com.template.anotations.EntityProperty;

public class FiltroComandaDTO {

	@EntityProperty("mesa.id")
	private Long mesaId;

	public Long getMesaId() {
		return mesaId;
	}

	public void setMesaId(Long mesaId) {
		this.mesaId = mesaId;
	}
}