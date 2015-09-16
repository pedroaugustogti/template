package br.com.localone.balanco.detalhe;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.balanco.BalancoPainelAbstract;
import br.com.localone.balanco.BalancoPainelDiario;
import br.com.localone.service.DespesaService;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Mes;
import br.com.template.entidades.Balanco;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.util.DataUtil;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="detalheMensal")
@ViewScoped
public class BalancoDetalheMensalController extends AbstractManageBean implements Serializable{
	
	private static final long serialVersionUID = 7087233480123661349L;

	private LineChartModel grafico;
	
	private Integer mesAtual;
	
	@EJB
	private DespesaService serviceDespesa;
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object objeSessao = getAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_DIARIO);
		
		if (existeBalancoMensal(objeSessao)){
			
			BalancoPainelDiario balancoDiario = (BalancoPainelDiario) objeSessao;
			
			mesAtual = balancoDiario.getMesAtual();
			
			montaGrafico(balancoDiario);
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}

	private boolean existeBalancoMensal(Object objeSessao) {
		return objeSessao != null && objeSessao instanceof BalancoPainelDiario;
	}
	
	private void montaGrafico(BalancoPainelDiario balancoDiario) {
		
		grafico = cria(balancoDiario);
		
		grafico.setTitle("Banalco financeiro do mês de "+ Mes.getNome(balancoDiario.getMesAtual())+ " de "+balancoDiario.getAnoAtual());
		grafico.setLegendPosition("e");
		grafico.setShowPointLabels(Boolean.TRUE);
		grafico.setMouseoverHighlight(Boolean.TRUE);
		grafico.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
	}

	private LineChartModel cria(BalancoPainelDiario balancoDiario) {

		LineChartModel graficoLinha = new LineChartModel();
		
		ChartSeries receita = new ChartSeries();
		receita.setLabel("Receita");
		ChartSeries despesa = new ChartSeries();
		despesa.setLabel("Despesa");
		
		moldaGraficoComTodosDias(receita, despesa, balancoDiario);
		
		for (Entry<String, List<Balanco>> map : balancoDiario.getMapDiario().entrySet()){
			
			int dia = BalancoPainelAbstract.diaPorChave(map.getKey());
			String labelDia = DataUtil.minimoDoisDigitos(dia);
			Double receitaDia = calcularFaturamentoDiario(map.getValue());
			
			receita.set(labelDia, receitaDia);
		}
		
		Double limiteDespesa = 0.0;
		
		for (int dia=01; dia < DataUtil.maiorDiaDoMes(balancoDiario.getAnoAtual(), mesAtual); dia ++){
			
			int ano = balancoDiario.getAnoAtual();
			int mes = mesAtual;
			String labelDia = DataUtil.minimoDoisDigitos(dia);
					
			Date dataInicio = DataUtil.criaData(ano,mes,dia);
			Date dataFinal = DataUtil.criaData(ano,mes,dia +1);
			
			DataUtil.setMenorHora(dataInicio);
			DataUtil.setMenorHora(dataFinal);
			
			Double despesaAtual = (Double) serviceDespesa.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
			
			if (despesaAtual != null && despesaAtual > limiteDespesa){
				
				limiteDespesa = despesaAtual;
			}
			
			despesa.set(labelDia, despesaAtual);
		}
		
		Axis yAxis = graficoLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Comparativo em R$");
		yAxis.setMin(BigInteger.ZERO);
		
		Double limiteReceita = balancoDiario.getMaiorFaturamentoDiario();
		
		if (limiteReceita > limiteDespesa){
			yAxis.setMax(limiteReceita + (limiteReceita * 0.2));
		}else{
			yAxis.setMax(limiteDespesa + (limiteDespesa * 0.2));
		}
		
		graficoLinha.setAnimate(Boolean.TRUE);
		graficoLinha.addSeries(receita);
		graficoLinha.addSeries(despesa);
		
		return graficoLinha;
	}


	private void moldaGraficoComTodosDias(ChartSeries receita, ChartSeries despesa, BalancoPainelDiario balancoDiario) {
		
		int maiorDiaMes = DataUtil.maiorDiaDoMes(balancoDiario.getAnoAtual(), balancoDiario.getMesAtual());
		
		for(int dia = 1; dia <= maiorDiaMes; dia++){
			
			String diaFormatado = DataUtil.minimoDoisDigitos(dia);
			
			receita.set(diaFormatado, null);
			despesa.set(diaFormatado, null);
		}
	}

	private Double calcularFaturamentoDiario(List<Balanco> listBalancoMensal) {
		
		Double faturamentoMensal = 0.0;
		
		for (Balanco balanco : listBalancoMensal){
			
			faturamentoMensal += balanco.getValorConta();
		}
		
		return faturamentoMensal;
	}

	public void analiseDespesa(){
		
	}
	
	public void analiseEstrategia(){
		
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.BALANCO_DETALHE_MENSAL;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}

	public Integer getAnoAtual() {
		return mesAtual;
	}
}