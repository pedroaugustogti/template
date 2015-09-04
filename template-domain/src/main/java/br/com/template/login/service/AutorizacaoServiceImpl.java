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

import br.com.template.domain.Mensagem;
import br.com.template.domain.Role;
import br.com.template.dto.FiltroUsuarioDTO;
import br.com.template.entidades.Usuario;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class AutorizacaoServiceImpl implements UserDetailsService {

	@EJB
	private ConsultasDaoJpa<Usuario> consultaDao;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		FiltroUsuarioDTO filtro = new FiltroUsuarioDTO();
		
		filtro.setUsuario(username);
	
		Usuario ususario = consultaDao.getPrimeiroRegistroPorFiltro(filtro, Usuario.class);
		
		if (ususario == null){
			throw new UsernameNotFoundException(Mensagem.MNG003.name());
		}
		
		Set<GrantedAuthority> authorities = buildUserAuthority(ususario.getRoles());

		return buildUserForAuthentication(ususario, authorities);
	}

	private User buildUserForAuthentication(Usuario user, Set<GrantedAuthority> authorities) {
		return new User(user.getUsuario(), user.getSenha(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>(0);

		for (Role userRole : roles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.name()));
		}

		return setAuths;
	}
}