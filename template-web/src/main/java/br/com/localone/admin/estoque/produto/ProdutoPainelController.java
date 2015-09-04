package br.com.localone.admin.estoque.produto;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ProdutoService;
import br.com.template.dto.FiltroProdutoDTO;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelProduto")
@ViewScoped
public class ProdutoPainelController extends AbstractManageBean{
	
	private Produto produto;
	private List<Produto> listProduto;
	private Produto produtoSelecionado;
	
	private FiltroProdutoDTO filtroProdutoDTO;
	
	@EJB
	private ProdutoService produtoService;
	
	@EJB
	private GenericServiceController<Produto, Long> service;
	
	@PostConstruct
	public void inicio(){
		
		produto = new Produto();
		filtroProdutoDTO = new FiltroProdutoDTO();
	}
	
	public void pesquisar(){
		
		listProduto = produtoService.pesquisar(filtroProdutoDTO);
	}
	
	public void redirecionaParaTelaAlterar(Produto produto) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_PRODUTO, produto);
		redirecionaPagina(Pagina.ALTERAR_PRODUTO);
	}
	
	public void excluir(){
		service.excluir(produtoSelecionado);
		this.pesquisar();
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_PRODUTO;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public FiltroProdutoDTO getFiltroProdutoDTO() {
		return filtroProdutoDTO;
	}

	public void setFiltroProdutoDTO(FiltroProdutoDTO filtroProdutoDTO) {
		this.filtroProdutoDTO = filtroProdutoDTO;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}