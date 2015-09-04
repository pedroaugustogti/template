package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Funcionario;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface FuncionarioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Funcionario> pesquisar(FiltroFuncionarioDTO filtro);
}
