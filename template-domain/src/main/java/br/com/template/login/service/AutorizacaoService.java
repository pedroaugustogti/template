package br.com.template.login.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.template.entidades.Usuario;

public interface AutorizacaoService {

	public boolean isAutorizado(Usuario usuario, final String role) throws UsernameNotFoundException;
}