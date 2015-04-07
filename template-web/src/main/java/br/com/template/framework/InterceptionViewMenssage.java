package br.com.template.framework;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.template.excecao.NegocioException;

public class InterceptionViewMenssage extends AbstractInterceptionMessage{

	@AroundInvoke
	public Object intercept(InvocationContext context) throws NegocioException {

		Object result = null;
		Boolean erro = Boolean.FALSE;
		Throwable cause = null;
		
		try {
			
			result = context.proceed();
			
		} catch (NegocioException e) {
			
			enviaMensagemPorTipo(e);
			erro = Boolean.TRUE;
			cause = e;
			
		} catch (Exception e) {
			e.printStackTrace();
			erro = Boolean.TRUE;
			cause = e;
			
		}finally{
			
			//Após tratar as mensagens, relança a exeção para cortar o fluxo de execução.
			if (erro){
				throw new NegocioException(cause);
			}
		}

		return result;
	}
}