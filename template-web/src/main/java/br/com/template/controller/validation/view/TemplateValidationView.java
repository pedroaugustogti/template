/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller.validation.view;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.dominio.MensagemNegocio;
import br.com.template.excecao.NegocioException;
import br.com.template.interceptors.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class TemplateValidationView {
	
	private final int LIMITE_CARACTERES = 3;
	
	public void verificarDigitosSuperiorTresCaracteres(String param) throws NegocioException{
		
		if (param != null && param.length() > LIMITE_CARACTERES){
			
			throw new NegocioException(MensagemNegocio.MNG001);
		}
	}

}
