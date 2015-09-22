package br.com.localone.admin.fornecedor;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.template.domain.Medida;
import br.com.template.dto.FiltroFornecedorDTO;
import br.com.template.entidades.Fornecedor;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class FornecedorSuperController extends AbstractManageBean{
	
	@EJB
	protected GenericServiceController<Fornecedor, Long> service;
	
	@EJB
	protected FornecedorValidadorView validacaoFornecedor;
	
	protected FiltroFornecedorDTO filtroFornecedorDTO;
	
	protected Fornecedor fornecedor;
	
	private int index;
	
	private Produto produto;
	
	@PostConstruct
	public void init(){
		produto = new Produto();
		filtroFornecedorDTO = new FiltroFornecedorDTO();
	}
	
	public boolean isMedidaUnidade() {
		return Medida.UNID.equals(produto.getMedida());
	}
	
	public void adicionarProduto(){
		
		if (fornecedor.getListProdutos() == null){
			
			fornecedor.setListProdutos(new ArrayList<Produto>());
		}
		
		try {
			
			validacaoFornecedor.validaProduto(fornecedor, produto);
			
			produto.setFornecedor(fornecedor);
			produto.setIndex(index);
			
			fornecedor.getListProdutos().add(index,produto);
			
			produto = new Produto();
			
			++index;
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	

	public void removerProduto(Produto produto){
		
		try {
			
			validacaoFornecedor.produtoVinculadoEstoque(produto);
			
			fornecedor.getListProdutos().remove(produto.getIndex());
			
			--index;
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	protected void adicionaIndexProduto(Fornecedor fornecedor) {
		
		if (fornecedor == null || fornecedor.getListProdutos() == null){
			return;
		}
			
		for (int i=0; i < fornecedor.getListProdutos().size(); i++){
			Produto produto = fornecedor.getListProdutos().get(i);
			
			produto.setIndex(i);
		}
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FiltroFornecedorDTO getFiltroFornecedorDTO() {
		return filtroFornecedorDTO;
	}

	public void setFiltroFornecedorDTO(FiltroFornecedorDTO filtroFornecedorDTO) {
		this.filtroFornecedorDTO = filtroFornecedorDTO;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}