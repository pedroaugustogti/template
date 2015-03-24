package br.com.template.login.service;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.template.entidades.Usuario;
import br.com.template.entidades.UsuarioRole;
import br.com.template.login.repository.UsuarioDao;

@Stateless
public class AutorizacaoServiceImpl implements UserDetailsService {

	@EJB
	private UsuarioDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		Usuario user = userDao.findByUserName(username);
		Set<GrantedAuthority> authorities = buildUserAuthority(user.getUsuarioRole());

		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(Usuario user, Set<GrantedAuthority> authorities) {
		return new User(user.getUsuario(), user.getSenha(), user.isAtivo(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Set<UsuarioRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>(0);

		for (UsuarioRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		return setAuths;
	}
}