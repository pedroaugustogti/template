package br.com.template.dto;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.SituacaoMesa;

public class FiltroMesaDTO {

	@EntityProperty("numMesa")
	private Integer numMesa;
	
	@EntityProperty("codigo")
	private String codigo;
	
	@EntityProperty("cpfCliente")
	private String cpfCliente;
	
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

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
}