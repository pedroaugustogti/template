package br.com.localone.admin.estoque;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ProdutoService;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="cadastroEstoque")
@ViewScoped
public class EstoqueCadastroController extends EstoqueSuperController{
	
	@EJB
	private GenericServiceController<Estoque, Long> service;
	
	@EJB
	private ProdutoService produtoService;
	
	@EJB
	private EstoqueValidacao estoqueValidacao;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void cadastrar(){
		
		try{	
			
			estoqueValidacao.validacao(estoque);
			service.salvar(estoque);
			inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_ESTOQUE;
	}
}