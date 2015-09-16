package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Empresa;
import br.com.template.domain.Role;

public class FiltroUsuarioDTO {

	@EntityProperty("usuario")
	private String usuario;
	
	@EntityProperty("empresa")
	private Empresa empresa;
	
	@EntityProperty("role")
	private Role role;
	
	private List<SelectItem> empresas;
	
	public FiltroUsuarioDTO(){
		empresas = Empresa.selectItems();
	}
	
	public String getUsuario() {
		return usuario;
	}

	public List<SelectItem> getEmpresas() {
		return empresas;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}