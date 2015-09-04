package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum Estado {

	DF("Distrito Federal"),
	GO("Goiás");
	
	private String label;
	
	private Estado(String label){
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
		
		for (Estado tipoProduto : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(tipoProduto.label);
			selectItem.setValue(tipoProduto);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}