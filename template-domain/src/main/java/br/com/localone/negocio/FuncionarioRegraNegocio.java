package br.com.localone.negocio;

import br.com.template.entidades.Funcionario;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface FuncionarioRegraNegocio {

	void proibeCadastroDoMesmoCPF(Funcionario funcionario) throws NegocioException;
}
