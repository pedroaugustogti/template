package br.com.localone.balanco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.template.domain.Mes;
import br.com.template.entidades.Balanco;
import br.com.template.util.DinheiroUtil;

public class BalancoPainelMensal extends BalancoPainelAbstract{

	private BalancoPainelAnual balancoAnual;
	
	private List<String> listBalancoMes;
	private Map<String,List<Balanco>> mapMensal;
	
	private Integer anoAtual;
	private Double maiorFaturamentoMensal;
	
	private String faturamentoMensal;
	
	public BalancoPainelMensal(BalancoPainelAnual balancoAnual){
		
		listBalancoMes = new ArrayList<String>();
		mapMensal = new HashMap<String, List<Balanco>>();
		
		this.balancoAnual = balancoAnual;
	}
	
	public void acordionMesesPorAno(Integer ano){
		
		anoAtual = ano;
		
		List<Balanco> listBalancoAnual = balancoAnual.getMapAnual().get(String.valueOf(ano));
		
		populaMapMesal(ano, listBalancoAnual);
		
		calculoFaturamentoAnual();
	}
	
	private void calculoFaturamentoAnual() {
		
		Double faturamentoAnual = 0.0;
		maiorFaturamentoMensal = 0.0;
		
		for (Entry<String, List<Balanco>> mapMes : mapMensal.entrySet()){
			
			Double faturamentoDoMes = 0.0;
			
			for (Balanco balanco : mapMes.getValue()){
				
				faturamentoAnual += balanco.getValorConta();
				faturamentoDoMes += balanco.getValorConta();
			}
			
			if (maiorFaturamentoMensal < faturamentoDoMes){
				
				maiorFaturamentoMensal = faturamentoDoMes;
			}
		}
		
		String faturamentoEmReal = DinheiroUtil.doubleEmReal(faturamentoAnual);
		balancoAnual.setFaturamentoAnual(faturamentoEmReal);
	}

	private void populaMapMesal(Integer ano, List<Balanco> listBalancoAnual) {
		
		listBalancoMes = new ArrayList<String>();
		mapMensal = new HashMap<String,List<Balanco>>();
		
		for (Balanco balanco : listBalancoAnual){
			
			int mes = balanco.getFechamentoConta().get(Calendar.MONTH);
			String mesChave = montaChaveMes(ano, mes);
			List<Balanco> balancoMes = mapMensal.get(mesChave);
			
			if (balancoMes == null || balancoMes.isEmpty()){
				
				balancoMes = new ArrayList<Balanco>();
				balancoMes.add(balanco);
				
				listBalancoMes.add(Mes.getNome(mes));
				mapMensal.put(mesChave, balancoMes);
			
			}else if (!balancoMes.isEmpty()){
				
				balancoMes.add(balanco);
			}
		}
	}

	public Integer getAnoAtual() {
		return anoAtual;
	}

	public List<String> getListBalancoMes() {
		return listBalancoMes;
	}

	public Map<String, List<Balanco>> getMapMensal() {
		return mapMensal;
	}

	public String getFaturamentoMensal() {
		return faturamentoMensal;
	}

	public void setFaturamentoMensal(String faturamentoMensal) {
		this.faturamentoMensal = faturamentoMensal;
	}

	public Double getMaiorFaturamentoMensal() {
		return maiorFaturamentoMensal;
	}

	public void setMaiorFaturamentoMensal(Double maiorFaturamentoMensal) {
		this.maiorFaturamentoMensal = maiorFaturamentoMensal;
	}
}