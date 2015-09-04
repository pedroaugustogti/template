package br.com.template.dto;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.SituacaoMesa;

public class FiltroMenuDTO {

	@EntityProperty("numMesa")
	private Integer numMesa;
	
	@EntityProperty("codigo")
	private String codigo;
	
	@EntityProperty("situacao")
	private SituacaoMesa situacaoMesa;

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public SituacaoMesa getSituacaoMesa() {
		return situacaoMesa;
	}

	public void setSituacaoMesa(SituacaoMesa situacaoMesa) {
		this.situacaoMesa = situacaoMesa;
	}
	
	
}