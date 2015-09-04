package br.com.localone.admin.estoque;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.EstoqueService;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelEstoque")
@ViewScoped
public class EstoquePainelController extends EstoqueSuperController{
	
	private List<Estoque> listEstoque;
	private Estoque estoqueSelecionado;
	
	@EJB
	private EstoqueService estoqueService;
	
	@EJB
	private GenericServiceController<Estoque, Long> service;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void pesquisar(){
		
		listEstoque = estoqueService.pesquisar(filtroEstoqueDTO);
	}
	
	public void redirecionaParaTelaAlterar(Estoque estoque) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_ESTOQUE, estoque);
		redirecionaPagina(Pagina.ALTERAR_ESTOQUE);
	}
	
	public void excluir(){
		service.excluir(estoqueSelecionado);
		this.pesquisar();
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_ESTOQUE;
	}

	public List<Estoque> getListEstoque() {
		return listEstoque;
	}

	public Estoque getEstoqueSelecionado() {
		return estoqueSelecionado;
	}

	public void setEstoqueSelecionado(Estoque estoqueSelecionado) {
		this.estoqueSelecionado = estoqueSelecionado;
	}
}