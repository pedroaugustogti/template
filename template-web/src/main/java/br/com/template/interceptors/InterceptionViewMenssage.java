package br.com.template.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.template.excecao.NegocioException;

public class InterceptionViewMenssage extends AbstractInterceptionMessage{

	@AroundInvoke
	public Object intercept(InvocationContext context) {

		Object result = null;
		
		try {
			result = context.proceed();
		} catch (NegocioException e) {
			
			enviaMensagemPorTipo(e);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}