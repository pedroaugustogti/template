package br.com.template.interceptors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.template.domain.MensagemNegocio;
import br.com.template.domain.TipoMensagem;
import br.com.template.excecao.NegocioException;
import br.com.template.util.InitMessageProperties;

public abstract class AbstractInterceptionMessage {

	protected void enviaMensagemPorTipo(NegocioException e) {
		
		final TipoMensagem TIPO = e.getTipo();
		
		if (TipoMensagem.INFO.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_INFO);
		}else if (TipoMensagem.ALERTA.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_WARN);
		}else if (TipoMensagem.ERRO.equals(TIPO)){
			
			enviaMensagem(e.getMensagem(),FacesMessage.SEVERITY_ERROR);
		}
	}

	protected void enviaMensagem(String mensagem,Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(severity,mensagem, null));
	}

	protected void enviaMensagemDefault(MensagemNegocio mensagemNegocio) {
		
		enviaMensagem(InitMessageProperties.getValue(mensagemNegocio), FacesMessage.SEVERITY_INFO);
	}
}