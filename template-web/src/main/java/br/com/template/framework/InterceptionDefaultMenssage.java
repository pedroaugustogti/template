package br.com.template.framework;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.template.domain.Mensagem;

public class InterceptionDefaultMenssage extends AbstractInterceptionMessage{

	@AroundInvoke
	public Object intercept(InvocationContext context) {

		Object result = null;
		
		try {
			result = context.proceed();
			enviaMensagemDefault(Mensagem.MDF001);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}