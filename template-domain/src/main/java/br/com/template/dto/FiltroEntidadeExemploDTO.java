package br.com.template.dto;

import br.com.template.anotations.EntityProperty;

public class FiltroEntidadeExemploDTO {
	
	@EntityProperty("id")
	private Long id;
	
	@EntityProperty("descricao")
    private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}