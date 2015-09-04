package br.com.localone.service;

import br.com.template.dto.FiltroComandaDTO;
import br.com.template.entidades.Comanda;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface ComandaService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	Comanda getComandaMesa(FiltroComandaDTO filtro);
}
