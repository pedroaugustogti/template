package br.com.localone.balanco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.template.entidades.Balanco;
import br.com.template.util.DataUtil;

public class BalancoPainelAnual extends BalancoPainelAbstract{
	
	private List<String> listBalancoAno;
	private Map<String,List<Balanco>> mapAnual;
	
	private String faturamentoAnual;
	
	public BalancoPainelAnual(){
		
		listBalancoAno = new ArrayList<String>();
		mapAnual = new HashMap<String,List<Balanco>>();
	}
	
	public void agrupaBalancoPorAno(List<Balanco> listPesquisaBalanco) {
		
		mapAnual = new HashMap<String,List<Balanco>>();
		listBalancoAno = new ArrayList<String>();
		
		for (Balanco balanco : listPesquisaBalanco){
			
			String ano = String.valueOf(DataUtil.ano(balanco.getFechamentoConta()));
			
			List<Balanco> balancosAno = mapAnual.get(ano);
			
			if (balancosAno == null || balancosAno.isEmpty()){
				
				balancosAno = new ArrayList<Balanco>();
				balancosAno.add(balanco);
				
				mapAnual.put(ano, balancosAno);
				
				listBalancoAno.add(ano);
				
			}else{
				
				balancosAno.add(balanco);
			}
		}
		
		Collections.sort(listBalancoAno);
		Collections.reverse(listBalancoAno);
	}
	
	public List<String> getListBalancoAno() {
		return listBalancoAno;
	}

	public Map<String, List<Balanco>> getMapAnual() {
		return mapAnual;
	}

	public String getFaturamentoAnual() {
		return faturamentoAnual;
	}

	public void setFaturamentoAnual(String faturamentoAnual) {
		this.faturamentoAnual = faturamentoAnual;
	}
}