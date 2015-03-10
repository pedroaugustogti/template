package br.com.template.interceptors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.template.excecao.NegocioException;
import br.com.template.mensagem.TipoMensagem;

public class InterceptionViewMenssage {

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

	private void enviaMensagemPorTipo(NegocioException e) {
		
		final TipoMensagem TIPO = e.getTipo();
		
		if (TipoMensagem.INFO.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_INFO);
		}else if (TipoMensagem.ALERTA.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_WARN);
		}else if (TipoMensagem.ERRO.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_ERROR);
		}
	}

	private void enviaMensagem(String mensagem,Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(severity,mensagem, null));
	}
}