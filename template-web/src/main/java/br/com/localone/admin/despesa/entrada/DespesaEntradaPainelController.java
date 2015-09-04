package br.com.localone.admin.despesa.entrada;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.DespesaEntradaService;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelDespesaEntrada")
@ViewScoped
public class DespesaEntradaPainelController extends DespesaEntradaSuperController{
	
	private List<DespesaEntrada> listDespesaEntrada;
	private DespesaEntrada despesaEntradaSelecionado;
	
	@EJB
	private DespesaEntradaService despesaEntradaService;
	
	@EJB
	private GenericServiceController<DespesaEntrada, Long> service;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void pesquisar(){
		
		listDespesaEntrada = despesaEntradaService.pesquisar(filtroDespesaEntradaDTO);
	}
	
	public void redirecionaParaTelaAlterar(DespesaEntrada despesaEntrada) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_DESPESA_ENTRADA, despesaEntrada);
		redirecionaPagina(Pagina.ALTERAR_DESPESA_ENTRADA);
	}
	
	public void excluir(){
		service.excluir(despesaEntradaSelecionado);
		this.pesquisar();
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_DESPESA_ENTRADA;
	}

	public List<DespesaEntrada> getListDespesaEntrada() {
		return listDespesaEntrada;
	}

	public DespesaEntrada getDespesaEntradaSelecionado() {
		return despesaEntradaSelecionado;
	}

	public void setDespesaEntradaSelecionado(DespesaEntrada despesaEntradaSelecionado) {
		this.despesaEntradaSelecionado = despesaEntradaSelecionado;
	}
}