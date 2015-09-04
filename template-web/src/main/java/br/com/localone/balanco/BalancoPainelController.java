package br.com.localone.balanco;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.BalancoService;
import br.com.template.entidades.Balanco;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelBalanco")
@ViewScoped
public class BalancoPainelController extends AbstractManageBean{
	
	@EJB
	private BalancoService balancoService;
	
	@EJB
	private BalancoPainelDespesa balancoDespesa;
	
	private Date dataInicioPesquisa;
	private Date dataFimPesquisa;
	
	private BalancoPainelAnual balancoAnual;
	
	private BalancoPainelMensal balancoMensal;
	private BalancoPainelDiario balancoDiario;
	private BalancoPainelHora balancoPainelHora;
	
	@PostConstruct
	public void inicio(){
		
		dataInicioPesquisa = new Date();
		dataFimPesquisa = new Date();
		
		balancoAnual = new BalancoPainelAnual();
		balancoMensal = new BalancoPainelMensal(balancoAnual);
		balancoDiario = new BalancoPainelDiario(balancoMensal);
		balancoPainelHora = new BalancoPainelHora(balancoDiario);
	}
	
	public void pesquisar(){
		
		List<Balanco> listBalanco = balancoService.pesquisar(dataInicioPesquisa, dataFimPesquisa);
		
		balancoAnual.agrupaBalancoPorAno(listBalanco);
	}
	
	public void detalheAnual() throws NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_MENSAL, balancoMensal);
		redirecionaPagina(Pagina.BALANCO_DETALHE_ANUAL);
	}
	
	public void detalheMensal() throws NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_DIARIO, balancoDiario);
		redirecionaPagina(Pagina.BALANCO_DETALHE_MENSAL);
	}
	
	public void detalheDiario(String chave) throws NegocioException{
		
		BalancoPainelHora balancoHora = new BalancoPainelHora(balancoDiario, chave);
		
		setAtributoSessao(AtributoSessao.OBJ_BALANCO_PAINEL_HORA, balancoHora);
		redirecionaPagina(Pagina.BALANCO_DETALHE_DIARIO);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_BALANCO;
	}
	
	public Date getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(Date dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Date getDataFimPesquisa() {
		return dataFimPesquisa;
	}

	public void setDataFimPesquisa(Date dataFimPesquisa) {
		this.dataFimPesquisa = dataFimPesquisa;
	}
	
	public BalancoPainelAnual getBalancoAnual() {
		return balancoAnual;
	}

	public BalancoPainelMensal getBalancoMensal() {
		return balancoMensal;
	}

	public BalancoPainelDiario getBalancoDiario() {
		return balancoDiario;
	}

	public BalancoPainelDespesa getBalancoDespesa() {
		return balancoDespesa;
	}

	public BalancoPainelHora getBalancoPainelHora() {
		return balancoPainelHora;
	}
}