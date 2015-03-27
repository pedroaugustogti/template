package br.com.template.navegacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.template.domain.Role;

public enum AutorizacaoEnum {

	ANONIMO(Pagina.LOGIN, 
			Pagina.CADASTRAR_USUARIO),
	
	ADMINISTRADOR(Role.ADMIN, Pagina.CADASTRAR_PESSOA, 
							  Pagina.CONSULTAR_PESSOA),
							  
	USUARIO(Role.USUARIO, Pagina.CONSULTAR_PESSOA);
	
	private Pagina[] paginasComPermissao;
	private Role role;
	
	private AutorizacaoEnum(Pagina... paginasComPermissao){
		
		this.paginasComPermissao = paginasComPermissao;
	}
	
	private AutorizacaoEnum(Role role, Pagina... paginasComPermissao){
		
		this.role = role;
		this.paginasComPermissao = paginasComPermissao;
	}
	
	public static boolean usuarioComAcesso(Pagina paginaAtual, List<SimpleGrantedAuthority> permissoesUsuarioLogado) {
		
		boolean usuarioComAcesso = Boolean.FALSE;
		
		if (paginaComAcessoAnonimo(paginaAtual)){
			
			usuarioComAcesso = Boolean.TRUE;
			
		}else if (permissoesUsuarioLogado != null){
			
			for (GrantedAuthority role : permissoesUsuarioLogado){
				
				if (rolesDisponiveis(paginaAtual).contains(role.getAuthority())){
					usuarioComAcesso = Boolean.TRUE;
					break;
				}
			}
		}
		
		return usuarioComAcesso;
	}

	private static boolean paginaComAcessoAnonimo(Pagina paginaAtual) {
		
		boolean permissaoAnonima = Boolean.FALSE;
		
		for (Pagina pagina : ANONIMO.paginasComPermissao){
			
			if (pagina.equals(paginaAtual)){
				
				permissaoAnonima = Boolean.TRUE;
				break;
			}
		}
		
		return permissaoAnonima;
	}
	
	private static List<String> rolesDisponiveis(Pagina pagina){
		
		List<String> autorizacaoDisponivel = new ArrayList<String>();
		
		novaAutorizacao:
		for (AutorizacaoEnum autorizacao : values()){
			
			for (Pagina paginaDisponivel : autorizacao.paginasComPermissao){
				
				if (paginaDisponivel.equals(pagina)){
					
					autorizacaoDisponivel.add(autorizacao.role.name());
					continue novaAutorizacao;
				}
			}
		}
		
		return autorizacaoDisponivel;
	}
}