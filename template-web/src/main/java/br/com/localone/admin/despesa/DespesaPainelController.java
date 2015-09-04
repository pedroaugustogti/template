package br.com.localone.admin.despesa;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.DespesaService;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

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
	
	@PostConstruct
	public void inicio(){
		
		despesa = new Despesa();
		filtroDespesaDTO = new FiltroDespesaDTO();
	}
	
	public void pesquisar(){
		
		listDespesa = despesaService.pesquisar(filtroDespesaDTO);
	}
	
	public void redirecionaParaTelaAlterar(Despesa despesa) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_DESPESA, despesa);
		redirecionaPagina(Pagina.ALTERAR_DESPESA);
	}
	
	public void excluir(){
		service.excluir(despesaSelecionado);
		this.pesquisar();
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