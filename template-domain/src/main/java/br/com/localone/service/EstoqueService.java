package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Estoque;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface EstoqueService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Estoque> pesquisar(FiltroEstoqueDTO filtro);
}
