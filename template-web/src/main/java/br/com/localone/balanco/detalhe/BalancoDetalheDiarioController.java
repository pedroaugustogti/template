package br.com.localone.balanco.detalhe;

import java.io.Serializable;
import java.math.BigInteger;
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
import br.com.localone.balanco.BalancoPainelHora;
import br.com.localone.service.DespesaEntradaService;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Mes;
import br.com.template.domain.Tempo;
import br.com.template.entidades.Balanco;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.util.DataUtil;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="detalheDiario")
@ViewScoped
public class BalancoDetalheDiarioController extends AbstractManageBean implements Serializable{
	
	private static final long serialVersionUID = 7087233480123661349L;

	private LineChartModel grafico;
	
	@EJB
	private DespesaEntradaService serviceDespesa;
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object objeSessao = getAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_HORA);
		
		if (existeBalancoDiario(objeSessao)){
			
			BalancoPainelHora balancoDiario = (BalancoPainelHora) objeSessao;
			
			montaGrafico(balancoDiario);
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}

	private boolean existeBalancoDiario(Object objeSessao) {
		return objeSessao != null && objeSessao instanceof BalancoPainelHora;
	}
	
	private void montaGrafico(BalancoPainelHora balancoHora) {
		
		grafico = cria(balancoHora);
		
		Integer dia = balancoHora.getDiaAtual();
		String mes = Mes.getNome(balancoHora.getBalancoDiario().getMesAtual());
		Integer ano = balancoHora.getBalancoDiario().getAnoAtual();
		
		grafico.setTitle("Banalco financeiro por hora do dia "+ dia + " do mês "+ mes +" de "+ano);
		grafico.setLegendPosition("e");
		grafico.setShowPointLabels(Boolean.TRUE);
		grafico.setMouseoverHighlight(Boolean.TRUE);
		grafico.getAxes().put(AxisType.X, new CategoryAxis("Horas"));
		
		Axis yAxis = grafico.getAxis(AxisType.Y);
		yAxis.setLabel("Comparativo em R$");
		yAxis.setMin(BigInteger.ZERO);
		
		Double limiteGrafico = balancoHora.getMaiorFaturamentoPorHora() + 1000;
		
		yAxis.setMax(limiteGrafico);
	}

	private LineChartModel cria(BalancoPainelHora balancoPainelHora) {

		LineChartModel graficoLinha = new LineChartModel();
		
		ChartSeries receita = new ChartSeries();
		receita.setLabel("Receita");
		
		moldaGraficoComTodosDias(receita);
		
		for (Entry<Integer, List<Balanco>> map : balancoPainelHora.getMapHora().entrySet()){
			
			String labelHora = DataUtil.minimoDoisDigitos(map.getKey());
			Double receitaHora = calcularFaturamentoHora(map.getValue());
			
			receita.set(labelHora, receitaHora);
		}
		
		graficoLinha.setAnimate(Boolean.TRUE);
		graficoLinha.addSeries(receita);
		
		return graficoLinha;
	}


	private void moldaGraficoComTodosDias(ChartSeries receita) {
		
		for(int hora = 0; hora <= Tempo.HORA.getTempoMaximo(); hora++){
			
			String horaFormatada = DataUtil.minimoDoisDigitos(hora);
			
			receita.set(horaFormatada, null);
		}
	}

	private Double calcularFaturamentoHora(List<Balanco> listBalancoHora) {
		
		Double faturamentoHora = 0.0;
		
		for (Balanco balanco : listBalancoHora){
			
			faturamentoHora += balanco.getValorConta();
		}
		
		return faturamentoHora;
	}

	public void analiseDespesa(){
		
	}
	
	public void analiseEstrategia(){
		
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.BALANCO_DETALHE_DIARIO;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}
}