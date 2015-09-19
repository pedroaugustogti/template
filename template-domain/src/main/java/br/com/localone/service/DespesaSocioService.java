package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroDespesaSocioDTO;
import br.com.template.entidades.DespesaSocio;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface DespesaSocioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<DespesaSocio> pesquisar(FiltroDespesaSocioDTO filtro,String... hibernateInitialize);
}
