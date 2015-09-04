package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum Cargo {

	COZINHEIRO("Cozinheiro"),
	GARCOM("Garçom"),
	CAIXA("Caixa"),
	GERENTE("Gerente");
	
	private String label;
	
	private Cargo(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}
	
	public static List<SelectItem> selectItems(){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (Cargo tipoProduto : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(tipoProduto.label);
			selectItem.setValue(tipoProduto);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}