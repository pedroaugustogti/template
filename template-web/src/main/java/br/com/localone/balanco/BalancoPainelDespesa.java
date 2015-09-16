package br.com.localone.balanco;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import br.com.localone.service.DespesaService;
import br.com.template.util.DataUtil;
import br.com.template.util.DinheiroUtil;

@Stateful
public class BalancoPainelDespesa {
	
	private String despesaAnual;
	private String despesaMensal;
	
	@EJB
	private DespesaService despesaService;
	
	public void calculaDespesaAnual(BalancoPainelMensal balanco){
		
		Double anual = 0.0;
		int anoAtual = balanco.getAnoAtual();
		final int PRIMEIRO_DIA_MES = 01;
		
		for (int mes=00; mes < 12; mes ++){
			
			Date dataInicio = DataUtil.criaData(anoAtual ,mes, PRIMEIRO_DIA_MES);
			Date dataFinal = DataUtil.criaData(anoAtual,mes, DataUtil.maiorDiaDoMes(anoAtual, mes));
			
			DataUtil.setMenorHora(dataInicio);
			DataUtil.setMaiorHora(dataFinal);
			
			Double despesaAnual = (Double)despesaService.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
			
			if (despesaAnual != null){
				
				anual += despesaAnual;
			}
		}
		
		despesaAnual = DinheiroUtil.doubleEmReal(anual);
		
	}
	
	public void calculaDespesaMensal(BalancoPainelDiario balancoDiario){
		
		int mes = balancoDiario.getMesAtual();
		int ano = balancoDiario.getAnoAtual();
		Double mensal = 0.0;
		
		for (int dia=01; dia < DataUtil.maiorDiaDoMes(ano, mes); dia ++){
			
			Date dataInicio = DataUtil.criaData(ano,mes,dia);
			Date dataFinal = DataUtil.criaData(ano,mes,dia +1);
			
			DataUtil.setMenorHora(dataInicio);
			DataUtil.setMenorHora(dataFinal);
			
			Double despesaMensal = (Double)despesaService.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
			
			if (despesaMensal != null){
				
				mensal += despesaMensal;
			}
		}
		
		despesaMensal = DinheiroUtil.doubleEmReal(mensal);
	}
	
	public String getDespesaAnual() {
		return despesaAnual;
	}

	public String getDespesaMensal() {
		return despesaMensal;
	}
}