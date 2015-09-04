package br.com.localone.service;

import java.util.Date;
import java.util.List;

import br.com.template.dto.FiltroDespesaEntradaDTO;
import br.com.template.entidades.DespesaEntrada;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface DespesaEntradaService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<DespesaEntrada> pesquisar(FiltroDespesaEntradaDTO filtro);

	Number pesquisaTotalDespesasPeloPeriodo(Date dataInicio, Date dataFinal);
}
