package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroFornecedorDTO;
import br.com.template.entidades.Fornecedor;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface FornecedorService {

	List<Fornecedor> pesquisar(FiltroFornecedorDTO filtro, String...hibernateInitialize);
}
