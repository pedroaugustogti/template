package br.com.localone.negocio;

import br.com.template.entidades.Cardapio;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface CardapioRegraNegocio {

	void proibeCadastroComMesmaDescricao(Cardapio cardapio) throws NegocioException;
}
