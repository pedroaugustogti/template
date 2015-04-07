package br.com.template.controller.pessoa;

import java.util.Date;

public class BeanRelatorio{
		
	private String nome;
	private String sexo;
	private Date dataNascimento;
	
	public BeanRelatorio(String nome, String sexo, Date dataNascimento) {
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
}