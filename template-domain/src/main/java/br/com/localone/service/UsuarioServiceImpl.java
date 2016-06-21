package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.domain.Empresa;
import br.com.template.domain.Role;
import br.com.template.domain.Situacao;
import br.com.template.dto.FiltroUsuarioDTO;
import br.com.template.entidades.Usuario;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class UsuarioServiceImpl implements UsuarioService{
	
	@EJB
	private ConsultasDaoJpa<Usuario> reposiroty;

	@Override
	public List<Usuario> pesquisar(FiltroUsuarioDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Usuario.class);
	}

	@Override
	public Usuario usuarioPorUsername(String username) {
		FiltroUsuarioDTO filtro = new FiltroUsuarioDTO();
		
		filtro.setUsuario(username);
		
		return reposiroty.primeiroRegistroPorFiltro(filtro, Usuario.class);
	}

	@Override
	public List<Usuario> usuariosAtivoComRoleAdmin(Empresa empresa) {
		
		FiltroUsuarioDTO filtro = new FiltroUsuarioDTO();
		
		filtro.setEmpresa(empresa);
		filtro.setRole(Role.ADMIN);
		filtro.setSituacao(Situacao.ATIVO);
		
		return reposiroty.filtrarPesquisa(filtro, Usuario.class);
	}
}