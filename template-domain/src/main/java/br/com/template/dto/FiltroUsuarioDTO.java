package br.com.template.dto;

import br.com.template.anotations.EntityProperty;

public class FiltroUsuarioDTO {

	@EntityProperty("usuario")
	private String usuario;
	
	@EntityProperty("funcionario.pessoa.cpf")
	private String cpf;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}