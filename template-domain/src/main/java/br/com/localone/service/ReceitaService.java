package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Receita;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface ReceitaService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Receita> pesquisar(FiltroReceitaDTO filtro, String... initializeHibernateParameters);
}
