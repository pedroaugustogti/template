package br.com.template.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum Medida {

	//subMedidas
	ML("ML"),
	G("Grama"),
	
	//Medidas
	KG("Kilo", G),
	LT("Litro",ML),
	
	UNID("Unidade");
	
	
	private String label;
	private Medida[] subMedida;
	
	private Medida(String label, Medida... subMedida){
		this.label = label;
		this.subMedida = subMedida;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}
	
	public static List<SelectItem> selectItems(){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (Medida medida : values()){
			
			enuns.add(getSelectItem(medida));
		}
		
		return enuns;
	}
	
	public static List<SelectItem> selectSubMedidaItems(Medida medida){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		enuns.add(getSelectItem(medida));
		
		if (medida.subMedida != null){
			
			for (Medida subMedida : medida.subMedida){
				
				enuns.add(getSelectItem(subMedida));
			}
		}
		
		return enuns;
	}
	
	private static SelectItem getSelectItem(Medida medida) {
		
		SelectItem selectItem = new SelectItem();
		
		selectItem.setLabel(medida.label);
		selectItem.setValue(medida);
		
		return selectItem;
	}

	public static boolean isSubMedida(Medida medida, Medida subMed) {
		
		boolean retorno = Boolean.FALSE;
		
		if (medida != null){
			
			for (Medida subMedida : medida.subMedida){
				
				if (subMedida.equals(subMed)){
					retorno = Boolean.TRUE;
				}
			}
		}
		
		return retorno;
	}
}