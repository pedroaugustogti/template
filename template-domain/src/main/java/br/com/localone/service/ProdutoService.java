package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroProdutoDTO;
import br.com.template.entidades.Produto;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface ProdutoService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Produto> pesquisar(FiltroProdutoDTO filtro);

}
