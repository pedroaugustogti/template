package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum TipoDespesa {

	GERAL("Geral"),
	IMPOSTO("Imposto"),
	MENSAL("Mensal");
	
	private String label;
	
	private TipoDespesa(String label){
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
		
		for (TipoDespesa tipo : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(tipo.label);
			selectItem.setValue(tipo);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}