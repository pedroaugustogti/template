package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum SituacaoPedido {

	FILA("Na fila"),
	PREPARO("Em preparo"),
	CANCELADO("Cancelado"),
	CONCLUIDO("Concluído"),
	ENTREGUE("Entregue");
	
	private String label;
	
	private SituacaoPedido(String label){
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
		
		for (SituacaoPedido enun : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(enun.label);
			selectItem.setValue(enun);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}
}