package br.com.template.dto;

import java.io.Serializable;

public class ComandaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1809259242101262231L;
	private Long idComanda;

	public Long getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(Long idComanda) {
		this.idComanda = idComanda;
	}
}