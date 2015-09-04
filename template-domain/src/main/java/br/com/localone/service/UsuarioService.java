package br.com.localone.service;

import java.util.List;

import br.com.template.dto.FiltroUsuarioDTO;
import br.com.template.entidades.Usuario;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface UsuarioService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Usuario> pesquisar(FiltroUsuarioDTO filtro);

	Usuario usuarioPorUsername(String objUsuarioSessao);
}
