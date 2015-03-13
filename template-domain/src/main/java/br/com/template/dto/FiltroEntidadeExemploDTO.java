package br.com.template.dto;

import java.util.Date;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Sexo;

public class FiltroEntidadeExemploDTO {
	
	@EntityProperty("nome")
    private String nome;
	
	@EntityProperty("dataNascimento")
    private Date dataNascimento;
	
	@EntityProperty("sexo")
	private Sexo sexo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}