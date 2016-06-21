package br.com.template.dto;

import java.io.Serializable;

public class PedidoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6298477125490012163L;
	
	private String titulo;
	private String status;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}