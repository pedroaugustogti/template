package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.ProdutoService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroProdutoDTO;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;

@Stateless
public class ProdutoRegraNegocioImpl implements ProdutoRegraNegocio{
	
	@EJB
	private ProdutoService service;

	@Override
	public void proibeCadastroComMesmaDescricao(Produto produto) throws NegocioException {
		
		if (produto.getId() == null){
			
			regraNegocioCasoCadastro(produto);
		}else{
			
			regraNegocioCasoAlteracao(produto);
		}
	}

	private void regraNegocioCasoAlteracao(Produto produto) throws NegocioException {
		
		List<Produto> listProduto = produtoPorDescricao(produto);
		
		if (!listProduto.isEmpty()){
			
			Produto produtoBancoDados = listProduto.iterator().next();
			
			if (!produto.getId().equals(produtoBancoDados.getId())){
				
				throw new NegocioException(Mensagem.MNG016);
			}
		}
	}

	private void regraNegocioCasoCadastro(Produto produto) throws NegocioException {
		
		List<Produto> listProduto = produtoPorDescricao(produto);
		
		if (!listProduto.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG016);
		}
	}

	private List<Produto> produtoPorDescricao(Produto produto) {
		
		FiltroProdutoDTO filtro = new FiltroProdutoDTO();
		
		filtro.setDescricaoExata(produto.getDescricao());
		
		List<Produto> listProduto = service.pesquisar(filtro);
		return listProduto;
	}
}