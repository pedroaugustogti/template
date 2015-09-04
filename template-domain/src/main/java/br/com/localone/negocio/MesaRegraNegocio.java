package br.com.localone.negocio;

import br.com.template.entidades.Mesa;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface MesaRegraNegocio {

	void proibeCadastroComMesmoNumero(Mesa mesa) throws NegocioException;
}
