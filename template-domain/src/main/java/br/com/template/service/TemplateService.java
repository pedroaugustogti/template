package br.com.template.service;

import java.util.List;

import br.com.template.dto.FiltroEntidadeExemploDTO;
import br.com.template.entidades.EntidadeExemplo;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface TemplateService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<EntidadeExemplo> pesquisar(FiltroEntidadeExemploDTO filtro);
}
