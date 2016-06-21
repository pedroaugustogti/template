package br.com.localone.balanco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.template.entidades.Balanco;

public class BalancoPainelHora {
	
	private BalancoPainelDiario balancoDiario;
	
	private Map<Integer,List<Balanco>> mapHora;
	
	private Double maiorFaturamentoPorHora;
	
	private Integer diaAtual;
	
	public BalancoPainelHora(BalancoPainelDiario balancoDiario, String chave) {
		
		this.balancoDiario = balancoDiario;
		
		diaAtual = BalancoPainelAbstract.diaPorChave(chave);
		
		divideBalancoPorHora(chave);
	}

	public BalancoPainelHora(BalancoPainelDiario balancoDiario) {
	
		this.balancoDiario = balancoDiario;
	}

	private void divideBalancoPorHora(String chave){
		
		List<Balanco> balancoDodia = balancoDiario.getMapDiario().get(chave);
		
		agrupaDiaEmHora(balancoDodia);
		
		calculoFaturamentoHora();
	}
	
	private void agrupaDiaEmHora(List<Balanco> listBalancoDoDia) {
		
		mapHora = new HashMap<Integer,List<Balanco>>();
		
		for (Balanco balanco : listBalancoDoDia){
			
			int hora = balanco.getFechamentoConta().get(Calendar.HOUR_OF_DAY);
			List<Balanco> balancoHora = mapHora.get(hora);
			
			if (balancoHora == null || balancoHora.isEmpty()){
				
				balancoHora = new ArrayList<Balanco>();
				balancoHora.add(balanco);
				
				mapHora.put(hora, balancoHora);
			
			}else if (!balancoHora.isEmpty()){
				
				balancoHora.add(balanco);
			}
		}
	}
	
	private void calculoFaturamentoHora() {
		
		Double faturamentoHora = 0.0;
		maiorFaturamentoPorHora = 0.0;
		
		for (Entry<Integer, List<Balanco>> mapMes : mapHora.entrySet()){
			
			Double faturamentoDaHora = 0.0;
			
			for (Balanco balanco : mapMes.getValue()){
				
				faturamentoHora += balanco.getValorConta();
				faturamentoDaHora += balanco.getValorConta();
			}
			
			if (maiorFaturamentoPorHora < faturamentoDaHora){
				
				maiorFaturamentoPorHora = faturamentoDaHora;
			}
		}
	}

	public BalancoPainelDiario getBalancoDiario() {
		return balancoDiario;
	}

	public Map<Integer, List<Balanco>> getMapHora() {
		return mapHora;
	}

	public Double getMaiorFaturamentoPorHora() {
		return maiorFaturamentoPorHora;
	}

	public Integer getDiaAtual() {
		return diaAtual;
	}
}