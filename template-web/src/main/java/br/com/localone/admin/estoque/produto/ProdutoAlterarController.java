package br.com.localone.admin.estoque.produto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarProduto")
@ViewScoped
public class ProdutoAlterarController extends AbstractManageBean{
	
	@EJB
	protected GenericServiceController<Produto, Long> service;
	
	private Produto produto;

	@EJB
	private ProdutoValidacao produtoValidacao;

	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object dadosProduto = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_PRODUTO);
		
		if (dadosProduto != null && dadosProduto instanceof Produto){
			
			produto = (Produto) dadosProduto;
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar() throws NegocioException{
		
		try {
			
			produtoValidacao.validacao(produto);
			service.salvar(produto);
			
			inicio();
			
			redirecionaPagina(Pagina.PAINEL_PRODUTO);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_PRODUTO);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public Produto getProduto() {
		return produto;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_PRODUTO;
	}
}