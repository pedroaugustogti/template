package br.com.template.dto;

import br.com.template.anotations.EntityProperty;

public class FiltroUsuarioDTO {

	@EntityProperty("usuario")
	private String usuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}