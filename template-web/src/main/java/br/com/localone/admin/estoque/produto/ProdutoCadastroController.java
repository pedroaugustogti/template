package br.com.localone.admin.estoque.produto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="cadastroProduto")
@ViewScoped
public class ProdutoCadastroController extends AbstractManageBean{
	
	private Produto produto;
	
	@EJB
	private GenericServiceController<Produto, Long> service;
	
	@EJB
	private ProdutoValidacao produtoValidacao;
	
	@PostConstruct
	public void inicio(){
		
		produto = new Produto();
	}
	
	public void cadastrar(){
		
		try {
			produtoValidacao.validacao(produto);
			service.salvar(produto);
			inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_PRODUTO;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}