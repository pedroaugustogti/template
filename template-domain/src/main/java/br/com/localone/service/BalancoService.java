package br.com.localone.service;

import java.util.Calendar;
import java.util.List;

import br.com.template.entidades.Balanco;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface BalancoService {

	List<Balanco> pesquisar(Calendar dataInicio, Calendar dataFim);
}
