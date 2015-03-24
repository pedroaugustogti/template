package br.com.template.login.repository;

import br.com.template.entidades.Usuario;

public interface UsuarioDao {

	Usuario findByUserName(String username);

}