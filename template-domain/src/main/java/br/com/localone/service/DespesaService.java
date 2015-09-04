package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface DespesaService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Despesa> pesquisar(FiltroDespesaDTO filtro);

}
