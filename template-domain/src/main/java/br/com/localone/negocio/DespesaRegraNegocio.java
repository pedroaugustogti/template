package br.com.localone.negocio;

import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface DespesaRegraNegocio {

	void proibeCadastroComMesmaDescricao(Despesa despesa) throws NegocioException;
}
