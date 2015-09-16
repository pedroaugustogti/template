package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum Empresa {

	LOCAL_ONE("Local One", 
			Cargo.CAIXA, 
			Cargo.COZINHEIRO, 
			Cargo.GARCOM, 
			Cargo.GERENTE),
			
	BALADAPP("BaladApp"),
	FABRICA_SOFTWARE("Fábrica de Software");
	
	private String label;
	private Cargo[] cargos;
	
	private Empresa(String label, Cargo... cargos){
		this.label = label;
		this.cargos = cargos;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}
	
	public static List<SelectItem> selectItems(){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (Empresa enun : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(enun.label);
			selectItem.setValue(enun);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}

	public Cargo[] getCargos() {
		return cargos;
	}

	public static List<SelectItem> cargosPorEmpresa(Empresa empresa) {
		
		if (empresa == null || empresa.cargos == null){
			 return null;
		}
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (Cargo enun : empresa.cargos){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(enun.getLabel());
			selectItem.setValue(enun);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}