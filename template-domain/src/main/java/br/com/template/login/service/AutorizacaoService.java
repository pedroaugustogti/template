package br.com.template.login.service;

import javax.ejb.Local;

import org.springframework.security.core.userdetails.UserDetailsService;

@Local
public interface AutorizacaoService extends UserDetailsService{

}