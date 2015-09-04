package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum SituacaoMesa {

	LIVRE("Livre"),
	OCUPADA("Ocupada");
	
	private String label;
	
	private SituacaoMesa(String label){
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
		
		for (SituacaoMesa enun : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(enun.label);
			selectItem.setValue(enun);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}