package br.com.template.controller.validation.view;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.domain.MensagemNegocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.interceptors.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class ControllerCadastroUsuarioValidationView {
	
	public void confirmaSenha(Usuario usuario, String confirmarSenha) throws NegocioException {
		
		verificaSenhasInformadas(usuario, confirmarSenha);
		
		if (!usuario.getSenha().equals(confirmarSenha)){
			throw new NegocioException(MensagemNegocio.MNG005);
		}
	}

	private void verificaSenhasInformadas(Usuario usuario, String confirmarSenha)throws NegocioException {
		
		if (preCondicaoDiferentesNull(usuario, confirmarSenha)){
			
			throw new NegocioException(MensagemNegocio.MNG004);
		}
	}

	private boolean preCondicaoDiferentesNull(Usuario usuario,String confirmarSenha) {
		return usuario == null || usuario.getSenha() == null || confirmarSenha == null;
	}
}
