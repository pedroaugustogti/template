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
import br.com.localone.balanco.BalancoPainelMensal;
import br.com.localone.service.DespesaEntradaService;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Mes;
import br.com.template.entidades.Balanco;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.util.DataUtil;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="detalheAnual")
@ViewScoped
public class BalancoDetalheAnualController extends AbstractManageBean implements Serializable{
	
	private static final long serialVersionUID = 7087233480123661349L;

	private LineChartModel grafico;
	
	private Integer anoAtual;
	
	@EJB
	private DespesaEntradaService despesaService;
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object objeSessaoMensal = getAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_MENSAL);
		
		if (existeBalancoMensal(objeSessaoMensal)){
			
			BalancoPainelMensal balancoMensal = (BalancoPainelMensal) objeSessaoMensal;
			
			anoAtual = balancoMensal.getAnoAtual();
			
			montaGrafico(balancoMensal);
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}

	private boolean existeBalancoMensal(Object objeSessao) {
		return objeSessao != null && objeSessao instanceof BalancoPainelMensal;
	}
	
	private void montaGrafico(BalancoPainelMensal balancoMensal) {
		
		grafico = cria(balancoMensal);
		
		grafico.setTitle("Banalco financeiro do ano de "+balancoMensal.getAnoAtual());
		grafico.setLegendPosition("e");
		grafico.setShowPointLabels(Boolean.TRUE);
		grafico.setMouseoverHighlight(Boolean.TRUE);
		grafico.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
	}

	private LineChartModel cria(BalancoPainelMensal balancoMensal) {

		LineChartModel graficoLinha = new LineChartModel();
		Double valorTotal = 0.0;
		
		ChartSeries receita = new ChartSeries();
		receita.setLabel("Receita");
		ChartSeries despesa = new ChartSeries();
		despesa.setLabel("Despesa");
		
		moldaGraficoComTodosMeses(receita, despesa);
		
		montaGraficoReceita(balancoMensal, receita, valorTotal);
		
		Double limiteReceita = balancoMensal.getMaiorFaturamentoMensal();
		Double limiteDespesa = 0.0;
		
		for (int mes=00; mes < 12; mes ++){
			
			String labelMes = Mes.getNome(mes);
					
			Date dataInicio = DataUtil.criaData(anoAtual,mes,01);
			Date dataFinal = DataUtil.criaData(anoAtual,mes, DataUtil.maiorDiaDoMes(anoAtual, mes));
			
			DataUtil.setMenorHora(dataInicio);
			DataUtil.setMaiorHora(dataFinal);
			
			Double despesaAtual = (Double) despesaService.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
			
			if (despesaAtual != null && despesaAtual > limiteDespesa){
				
				limiteDespesa = despesaAtual;
			}
			
			despesa.set(labelMes, despesaAtual);
		}
		
		Axis yAxis = graficoLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Comparativo em R$");
		yAxis.setMin(BigInteger.ZERO);
		
		if (limiteDespesa > limiteReceita){
			yAxis.setMax(limiteDespesa + (limiteDespesa * 0.2));
		}else{
			yAxis.setMax(limiteReceita + (limiteReceita * 0.2));
		}
		
		
		graficoLinha.setAnimate(Boolean.TRUE);
		graficoLinha.addSeries(receita);
		graficoLinha.addSeries(despesa);
		
		return graficoLinha;
	}

	private void montaGraficoReceita(BalancoPainelMensal balancoMensal,ChartSeries receita, Double valorTotal) {
		
		for (Entry<String, List<Balanco>> mapMensal : balancoMensal.getMapMensal().entrySet()){
			
			int mes = BalancoPainelAbstract.mesPorChave(mapMensal.getKey());
			String labelMes = Mes.getNome(mes);
			Double receitaMes = calcularFaturamentoMensal(mapMensal.getValue());
			
			receita.set(labelMes, receitaMes);
		}
	}

	private void moldaGraficoComTodosMeses(ChartSeries receita, ChartSeries despesa) {
		
		for (Mes mes : Mes.values()){
			
			receita.set(mes.getLabel(), null);
			despesa.set(mes.getLabel(), null);
		}
	}

	private Double calcularFaturamentoMensal(List<Balanco> listBalancoMensal) {
		
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
		return Pagina.BALANCO_DETALHE_ANUAL;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}

	public Integer getAnoAtual() {
		return anoAtual;
	}
}