package br.com.template.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;


public enum Mes {

	JANEIRO("Janeiro",Calendar.JANUARY),
	FEVEREIRO("Fevereiro",Calendar.FEBRUARY),
	MARCO("Março",Calendar.MARCH),
	ABRIL("Abril",Calendar.APRIL),
	MAIO("Maio",Calendar.MAY),
	JUNHO("Junho",Calendar.JUNE),
	JULHO("Julho",Calendar.JULY),
	AGOSTO("Agosto",Calendar.AUGUST),
	SETEMBRO("Setembro",Calendar.SEPTEMBER),
	OUTBRO("Outubro",Calendar.OCTOBER),
	NOVEMBRO("Novembro",Calendar.NOVEMBER),
	DEZEMBRO("Dezembro",Calendar.DECEMBER);
	
	private String label;
	private int monthCalendar;
	
	private Mes(String label, int monthCalendar){
		this.label = label;
		this.monthCalendar = monthCalendar;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return this.name();
	}
	
	public static List<SelectItem> selectItems(){
		
		List<SelectItem> enuns = new ArrayList<SelectItem>();
		
		for (Mes enun : values()){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(enun.label);
			selectItem.setValue(enun);
			
			enuns.add(selectItem);
		}
		
		return enuns;
	}

	public static String getNome(int mes) {
		
		for (Mes enun : values()){
			
			if (enun.getMonthCalendar() == mes){
				return enun.label;
			}
		}
		
		return null;
	}

	public int getMonthCalendar() {
		return monthCalendar;
	}

	public static Integer getNumCalendarPorLabel(String mes) {
		
		for (Mes enun : values()){
			
			if (enun.getLabel().equals(mes)){
				return enun.getMonthCalendar();
			}
		}
		
		return null;
	}
}