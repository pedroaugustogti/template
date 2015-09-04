package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.entidades.Cardapio;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface CardapioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Cardapio> pesquisar(FiltroCardapioDTO filtro);
}
