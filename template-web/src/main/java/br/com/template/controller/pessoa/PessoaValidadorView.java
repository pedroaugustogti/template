/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller.pessoa;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.domain.Mensagem;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class PessoaValidadorView {
	
	private final int LIMITE_CARACTERES = 20;
	
	public void verificarExcessoCaracteres(String param) throws NegocioException{
		
		if (param != null && param.length() > LIMITE_CARACTERES){
			
			throw new NegocioException(Mensagem.MNG001);
		}
	}
}
