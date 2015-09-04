package br.com.localone.negocio;

import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface EstoqueRegraNegocio {

	void proibeCadastroDoMesmoProduto(Estoque estoque) throws NegocioException;
}
