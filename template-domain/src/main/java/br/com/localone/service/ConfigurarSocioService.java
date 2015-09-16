package br.com.localone.service;

import br.com.template.domain.Empresa;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.excecao.NegocioException;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface ConfigurarSocioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioException 
	 */
	ConfigurarSocio configuracaoPorEmpresa(Empresa empresa) throws NegocioException;
}
