package br.com.localone.admin.fornecedor;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.ProdutoRegraNegocio;
import br.com.template.domain.Medida;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Fornecedor;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class FornecedorValidadorView extends AbstractValidacao{
	
	@EJB
	private ProdutoRegraNegocio produtoRegraNegocio;
	
	public void valida(Fornecedor fornecedor) throws NegocioException {
		
		validaCamposObrigatorios(fornecedor);
	}
	
	private void validaCamposObrigatorios(Fornecedor fornecedor) throws NegocioException {
		
		if (StringUtils.isBlank(fornecedor.getNome())){
			throw new NegocioException(Mensagem.MNG065);
		}
	}
	
	public void validaProduto(Fornecedor fornecedor, Produto produto) throws NegocioException {
		
		if (StringUtils.isBlank(produto.getMarca())){
			throw new NegocioException(Mensagem.MNG066);
		}
		
		if (produto.getTipoProduto() == null){
			throw new NegocioException(Mensagem.MNG069);
		}
		
		if (produto.getMedida() == null){
			throw new NegocioException(Mensagem.MNG067);
		}
		
		if (!Medida.UNID.equals(produto.getMedida()) && inteiroNaoInformado(produto.getQuantidadeMedida())){
			throw new NegocioException(Mensagem.MNG068);
		}
		
		validaProdutoJaNaLista(fornecedor.getListProdutos(), produto);
	}

	private void validaProdutoJaNaLista(List<Produto> listProdutos, Produto produto) throws NegocioException {
		
		if (listProdutos == null || listProdutos.isEmpty()){
			return;
		}
		
		for (Produto prod : listProdutos){
			
			if (mesmoFornecedor(produto, prod) && 
					mesmaMarca(produto, prod) && 
					mesmaMedida(produto, prod) &&
					mesmaQuantidadeMedida(produto, prod)){
				
				throw new NegocioException(Mensagem.MNG070);
			}
		}
	}

	private boolean mesmaQuantidadeMedida(Produto produto, Produto prod) {
		return prod.getQuantidadeMedida().equals(produto.getQuantidadeMedida());
	}

	private boolean mesmaMedida(Produto produto, Produto prod) {
		return prod.getMedida().equals(produto.getMedida());
	}

	private boolean mesmaMarca(Produto produto, Produto prod) {
		return prod.getMarca().equals(produto.getMarca());
	}

	private boolean mesmoFornecedor(Produto produto, Produto prod) {
		return prod.getFornecedor().getNome().equals(produto.getFornecedor().getNome());
	}

	public void produtoVinculadoEstoque(Produto produto) throws NegocioException{
		
		produtoRegraNegocio.proibeExclusaoProdutoVinculadoEstoque(produto);
	}
}