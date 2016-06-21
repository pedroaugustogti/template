package br.com.localone.service;

import java.util.List;

import javax.ejb.Local;

import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Funcionario;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
@Local
public interface FuncionarioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Funcionario> pesquisar(FiltroFuncionarioDTO filtro);
}
