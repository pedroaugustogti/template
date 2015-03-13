package br.com.template.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.template.domain.MensagemNegocio;

public class InterceptionDefaultMenssage extends AbstractInterceptionMessage{

	@AroundInvoke
	public Object intercept(InvocationContext context) {

		Object result = null;
		
		try {
			result = context.proceed();
			enviaMensagemDefault(MensagemNegocio.MDF001);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}