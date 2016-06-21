package br.com.localone.admin.gastos.despesa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.DespesaService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.DinheiroUtil;

@ManagedBean(name="painelDespesa")
@ViewScoped
public class DespesaPainelController extends AbstractManageBean{
	
	private Despesa despesa;
	private List<Despesa> listDespesa;
	private Despesa despesaSelecionado;
	
	private FiltroDespesaDTO filtroDespesaDTO;
	
	@EJB
	private DespesaService despesaService;
	
	@EJB
	private GenericServiceController<Despesa, Long> service;
	
	@EJB
	private GenericServiceController<ConfigurarSocio, Long> serviceConfiguracaoSocio;
	
	@PostConstruct
	public void inicio(){
		
		despesa = new Despesa();
		filtroDespesaDTO = new FiltroDespesaDTO();
	}
	
	public void pesquisar(){
		
		listDespesa = despesaService.pesquisar(filtroDespesaDTO,"listDespesaSocio");
	}
	
	public void verificaConfiguracaoSocio(){
		
		try {
			
			if (serviceConfiguracaoSocio.listarTodos(ConfigurarSocio.class).isEmpty()){
				
				enviaMensagem(Mensagem.MNG051);
				return;
			}
			
			redirecionaPagina(Pagina.CADASTRAR_DESPESA);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public String calculoTotalDespesa(List<DespesaSocio> listDespesaSocio){
		
		double valorTotal = 0.0;
		
		for (DespesaSocio despesaSocio: listDespesaSocio){
			
			valorTotal += despesaSocio.getValor();
		}
		
		return DinheiroUtil.doubleEmRealSemSimbolo(valorTotal);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_DESPESA;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getListDespesa() {
		return listDespesa;
	}

	public FiltroDespesaDTO getFiltroDespesaDTO() {
		return filtroDespesaDTO;
	}

	public Despesa getDespesaSelecionado() {
		return despesaSelecionado;
	}

	public void setDespesaSelecionado(Despesa despesaSelecionado) {
		this.despesaSelecionado = despesaSelecionado;
	}
}