package br.com.localone.negocio;

import br.com.template.entidades.Produto;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface ProdutoRegraNegocio {

	void proibeExclusaoProdutoVinculadoEstoque(Produto produto) throws NegocioException;
}
