package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroMesaDTO;
import br.com.template.entidades.Mesa;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface MesaService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Mesa> pesquisar(FiltroMesaDTO filtro);
}