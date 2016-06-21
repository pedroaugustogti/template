package br.com.localone.balanco;

import java.util.Calendar;
import java.util.List;

import br.com.template.domain.Semana;
import br.com.template.entidades.Balanco;

public abstract class BalancoPainelAbstract {
	
	protected enum PeriodoChave {
		
		DIA(2),
		MES(1),
		ANO(0);
		
		int posicaoSplit;
		
		private PeriodoChave(int posicaoSplit){
			
			this.posicaoSplit = posicaoSplit;
		}

		public int getPosicaoSplit() {
			return posicaoSplit;
		}
	};
	
	protected static final String SEPARADOR = "-";
	
	protected String montaChaveMes(int ano, int mes) {
		
		return ano +SEPARADOR+ mes;
	}
	
	public static int mesPorChave(String chave) {
		return getPeriodoPorChave(chave, PeriodoChave.MES);
	}
	
	public static int anoPorChave(String chave) {
		return getPeriodoPorChave(chave, PeriodoChave.ANO);
	}
	
	public static int diaPorChave(String chave) {
		return getPeriodoPorChave(chave, PeriodoChave.DIA);
	}
	
	protected String montaChaveDia(String chaveBalancoMes, int dia) {
		return chaveBalancoMes+SEPARADOR+dia;
	}

	protected static int getPeriodoPorChave(String chave, PeriodoChave periodo) {
		return Integer.valueOf(chave.split(SEPARADOR)[periodo.posicaoSplit]);
	}
	
	protected String getDiaSemanaPorChave(String chave) {
		
		int dia = getPeriodoPorChave(chave, PeriodoChave.DIA);
		int mes = getPeriodoPorChave(chave, PeriodoChave.MES);
		int ano = getPeriodoPorChave(chave, PeriodoChave.ANO);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes, dia);
		 
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		
		return Semana.getLabel(diaSemana);
	}

	protected Double calcularValorFaturado(List<Balanco> list) {
		
		Double valorFaturado = 0.0;
		
		for (Balanco balanco : list){
			
			valorFaturado += balanco.getValorConta();
		}
		
		return valorFaturado;
	}
}