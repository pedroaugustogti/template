package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.EstoqueService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;

@Stateless
public class EstoqueRegraNegocioImpl implements EstoqueRegraNegocio{
	
	@EJB
	private EstoqueService service;

	@Override
	public void proibeCadastroDoMesmoProduto(Estoque estoque) throws NegocioException {
		
		if (estoque.getId() == null){
			
			regraNegocioCasoCadastro(estoque);
			
		}else{
			
			regraNegocioCasoAlteracao(estoque);
		}
	}

	private void regraNegocioCasoAlteracao(Estoque estoque) throws NegocioException {
		
		List<Estoque> listEstoque = getEstoquePorIdProduto(estoque);
		
		if (!listEstoque.isEmpty()){
			
			Estoque estoqueBancoDados = listEstoque.iterator().next();
			
			if (!estoque.getId().equals(estoqueBancoDados.getId())){
				
				throw new NegocioException(Mensagem.MNG016);
			}
		}
	}

	private void regraNegocioCasoCadastro(Estoque estoque) throws NegocioException {
		
		List<Estoque> listEstoque = getEstoquePorIdProduto(estoque);
		
		if (!listEstoque.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG016);
		}
	}

	private List<Estoque> getEstoquePorIdProduto(Estoque estoque) {
		
		FiltroEstoqueDTO filtro = new FiltroEstoqueDTO();
		filtro.setProdutoId(estoque.getProduto().getId());
		List<Estoque> listEstoque = service.pesquisar(filtro);
		
		return listEstoque;
	}
}