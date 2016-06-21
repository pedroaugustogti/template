package br.com.template.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

public enum CategoriaMenu {

	PRATO("Prato"),
	PETISCO("Petisco"),
	ENTRADA("Entrada"),
	BEBIDA("Bebida");
	
	private String label;
	
	private CategoriaMenu(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}

	public static List<CategoriaMenu> categorias() {
		return Arrays.asList(values());
	}
	
	public static List<SelectItem> selectItems(){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (CategoriaMenu value : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(value.label);
			selectItem.setValue(value);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}

	public static CategoriaMenu categoriaPorName(String categoriaName) {
		
		CategoriaMenu categoria = null;
		
		if (StringUtils.isBlank(categoriaName)){
			return categoria;
		}
		
		for (CategoriaMenu value : values()){
			
			if (value.name().equals(categoriaName)){
				categoria = value;
				break;
			}
		}

		return categoria;
	}
}