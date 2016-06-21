package br.com.localone.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.EstoqueService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;

@Stateless
public class ProdutoRegraNegocioImpl implements ProdutoRegraNegocio{
	
	@EJB
	private EstoqueService service;

	@Override
	public void proibeExclusaoProdutoVinculadoEstoque(Produto produto) throws NegocioException {
		
		FiltroEstoqueDTO filtroEstoqueDTO = new FiltroEstoqueDTO();
		
		filtroEstoqueDTO.setProdutoId(produto.getId());
		
		if (!service.pesquisar(filtroEstoqueDTO).isEmpty()){
			
			throw new NegocioException(Mensagem.MNG071);
		}
	}
}