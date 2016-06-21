package br.com.localone.admin.fornecedor.produto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ProdutoService;
import br.com.template.dto.FiltroProdutoDTO;
import br.com.template.entidades.Produto;
import br.com.template.framework.AbstractManageBean;

@ManagedBean(name="painelProduto")
@ViewScoped
public class ProdutoPainelController extends AbstractManageBean{
	
	private Produto produto;
	private List<Produto> listProduto;
	private FiltroProdutoDTO filtroProdutoDTO;
	
	@EJB
	private ProdutoService produtoService;
	
	@PostConstruct
	public void inicio(){
		
		produto = new Produto();
		filtroProdutoDTO = new FiltroProdutoDTO();
	}
	
	public void pesquisar(){
		
		listProduto = produtoService.pesquisar(filtroProdutoDTO);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_FORNECEDOR;
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
}