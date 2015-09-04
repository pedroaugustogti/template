package br.com.localone.balanco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.template.domain.Mes;
import br.com.template.entidades.Balanco;
import br.com.template.util.DataUtil;
import br.com.template.util.DinheiroUtil;

public class BalancoPainelDiario extends BalancoPainelAbstract{
	
	private BalancoPainelMensal balancoMensal;
	
	private List<BalancoPainelDia> listBalancoDia;
	private Map<String,List<Balanco>> mapDiario;
	
	private Integer mesAtual;
	private Integer anoAtual;
	private Double maiorFaturamentoDiario;
	
	public BalancoPainelDiario(BalancoPainelMensal balancoMensal){
		
		listBalancoDia = new ArrayList<BalancoPainelDia>();
		mapDiario = new HashMap<String, List<Balanco>>();
		
		this.balancoMensal = balancoMensal;
	}
	
	public void acordionDiasPorMesAno(String labelMes, Integer ano){
		
		int mes = Mes.getNumCalendarPorLabel(labelMes);
		
		mesAtual = mes;
		anoAtual = ano;
		
		String chaveMes = montaChaveMes(ano, mes);
		List<Balanco> balancoMensal = getMapMensal().get(chaveMes);
		
		listBalancoDia = agrupaMesEmDia(balancoMensal, chaveMes);
		
		calculoFaturamentoMensal();
	}
	
	private void calculoFaturamentoMensal() {
		
		Double faturamentoMensal = 0.0;
		maiorFaturamentoDiario = 0.0;
		
		for (Entry<String, List<Balanco>> mapMes : mapDiario.entrySet()){
			
			Double faturamentoDoDia = 0.0;
			
			for (Balanco balanco : mapMes.getValue()){
				
				faturamentoMensal += balanco.getValorConta();
				faturamentoDoDia += balanco.getValorConta();
			}
			
			if (maiorFaturamentoDiario < faturamentoDoDia){
				
				maiorFaturamentoDiario = faturamentoDoDia;
			}
		}
		
		String faturamentoEmReal = DinheiroUtil.doubleEmReal(faturamentoMensal);
		balancoMensal.setFaturamentoMensal(faturamentoEmReal);
	}
	
	private List<BalancoPainelDia> agrupaMesEmDia(List<Balanco> listBalancoMensal, String chaveBalancoMes) {
		
		mapDiario = new HashMap<String,List<Balanco>>();
		listBalancoDia = new ArrayList<BalancoPainelDia>();
		
		populaMapDiario(listBalancoMensal, chaveBalancoMes);
		
		for (Entry<String, List<Balanco>> entry : mapDiario.entrySet()){
			
			BalancoPainelDia balancoDia = populaBalancoDiario(entry);
			
			listBalancoDia.add(balancoDia);
		}
		
		return listBalancoDia;
	}

	private BalancoPainelDia populaBalancoDiario(Entry<String, List<Balanco>> entry) {
		
		BalancoPainelDia balancoDia = new BalancoPainelDia();
		
		//formato da chave yyyy-mm-dd
		String chave = entry.getKey();
		List<Balanco> listBalancoDoDia = mapDiario.get(chave);
		Double valorFaturado = calcularValorFaturado(listBalancoDoDia);
		String valorFaturadoEmReal = DinheiroUtil.doubleEmReal(valorFaturado);
		String diaSemana = getDiaSemanaPorChave(chave);
		
		int dia = getPeriodoPorChave(chave, PeriodoChave.DIA);
		String diaMes = DataUtil.minimoDoisDigitos(dia);
		
		balancoDia.setChave(chave);
		balancoDia.setDiaDaSemana(diaSemana);
		balancoDia.setDiaMes(diaMes);
		balancoDia.setValorFaturado(valorFaturadoEmReal);
		
		return balancoDia;
	}

	private void populaMapDiario(List<Balanco> listBalancoMes, String chaveBalancoMes) {
		
		for (Balanco balanco : listBalancoMes){
			
			int dia = DataUtil.dia(balanco.getFechamentoConta());
			String diaChave = montaChaveDia(chaveBalancoMes, dia);
			List<Balanco> balancoDiario = mapDiario.get(diaChave);
			
			if (balancoDiario == null || balancoDiario.isEmpty()){
				
				balancoDiario = new ArrayList<Balanco>();
				balancoDiario.add(balanco);
				
				mapDiario.put(diaChave, balancoDiario);
			
			}else if (!balancoDiario.isEmpty()){
				
				balancoDiario.add(balanco);
			}
		}
	}

	private Map<String, List<Balanco>> getMapMensal() {
		return balancoMensal.getMapMensal();
	}

	public List<BalancoPainelDia> getListBalancoDia() {
		return listBalancoDia;
	}

	public Map<String, List<Balanco>> getMapDiario() {
		return mapDiario;
	}

	public Integer getMesAtual() {
		return mesAtual;
	}

	public Double getMaiorFaturamentoDiario() {
		return maiorFaturamentoDiario;
	}

	public Integer getAnoAtual() {
		return anoAtual;
	}

	public BalancoPainelMensal getBalancoMensal() {
		return balancoMensal;
	}
}