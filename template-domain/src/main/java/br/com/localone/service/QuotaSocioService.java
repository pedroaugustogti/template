package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroQuotaSocioDTO;
import br.com.template.entidades.QuotaSocio;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface QuotaSocioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<QuotaSocio> pesquisar(FiltroQuotaSocioDTO filtro,String... hibernateInitialize);
}
