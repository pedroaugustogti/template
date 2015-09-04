package br.com.localone.service;

import java.util.Date;
import java.util.List;

import br.com.template.entidades.Balanco;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface BalancoService {

	List<Balanco> pesquisar(Date dataInicio, Date dataFim);
}
