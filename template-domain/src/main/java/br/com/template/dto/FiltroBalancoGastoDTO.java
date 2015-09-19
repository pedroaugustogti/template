package br.com.template.dto;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.template.anotations.EntityProperty;
import br.com.template.domain.Empresa;

public class FiltroBalancoGastoDTO {
	
	@EntityProperty("empresa")
	private Empresa empresa;
	
	private List<SelectItem> empresas;
	
	public FiltroBalancoGastoDTO(){
		empresas = Empresa.selectItems();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<SelectItem> getEmpresas() {
		return empresas;
	}
}