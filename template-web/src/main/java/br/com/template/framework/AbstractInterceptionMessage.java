package br.com.template.framework;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.template.domain.Mensagem;
import br.com.template.domain.TipoMensagem;
import br.com.template.excecao.NegocioException;
import br.com.template.util.InitMessageProperties;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Classe pai de todos os interceptors de mensagens </p>
 * 
 * Funcionalidades:
 * 
 * <ul>
 * 	<li>Enviar mensagens a tela do usuario através da exeção {@link NegocioException}</li>
 * 	<li>Enviar mensagens a tela do usuario pelo tipo da mensagem</li>
 *  <li>Envia mensagem automática que estiver como tipo DEFAULT</li>
 * </ul>
 *
 */
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

	protected void enviaMensagemDefault(Mensagem mensagemNegocio) {
		enviaMensagem(InitMessageProperties.getValue(mensagemNegocio), FacesMessage.SEVERITY_INFO);
	}
}