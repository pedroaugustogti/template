package br.com.template.framework;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.template.autorizacao.AutorizacaoEnum;
import br.com.template.autorizacao.FuncionalidadeEnum;
import br.com.template.autorizacao.Pagina;
import br.com.template.autorizacao.TipoFuncionalidadeEnum;
import br.com.template.domain.Mensagem;
import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;
import br.com.template.util.relatorio.AbstractParametrosRelatorio;
import br.com.template.util.relatorio.RelatorioUtil;

public abstract class AbstractManageBean extends AutorizacaoManageBean {
	
	private AutorizacaoEnum autorizacao;
	
	@PostConstruct
	@Override
	protected void validaPermissionamento() throws IOException, NegocioException {
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	autorizacao = AutorizacaoEnum.usuarioComAcesso(getPaginaManageBean(), permissoesUsuarioLogado());
    	
    	if (autorizacao == null || !authentication.isAuthenticated()) {
    		
    		logout();
        }
	}
	
	public boolean autorizaFuncionalidade(TipoFuncionalidadeEnum tipoFuncionalidade){
		
		return FuncionalidadeEnum.verificaAutorizacaoComAcessoNaFuncionalidade(autorizacao, tipoFuncionalidade);
	}

	public void logout() throws NegocioException {
		
		redirecionaPagina(Pagina.LOGIN);
		getHttpSession().invalidate();
	}
	
	@SuppressWarnings("unchecked")
	public void renderizaMensagensDaSessao() {
		
		Object listaMensagensSessao = getAtributoSessao(AtributoSessao.MENSAGEM);
		
		if (listaMensagensSessao != null && listaMensagensSessao instanceof List){
			
			List<FacesMessage> mensagens = (List<FacesMessage>) listaMensagensSessao;
			
			for (FacesMessage msg : mensagens){
				context().addMessage(null, msg);
			}
			
			limparAtributoDaSessao(AtributoSessao.MENSAGEM);
		}
	}

	protected abstract Pagina getPaginaManageBean();
	
	protected FacesContext context(){
		return FacesContext.getCurrentInstance();
	}
	
	protected ExternalContext externalContext() {
		return context().getExternalContext();
	}
	
	protected HttpServletResponse getHttpResponse(){
		return (HttpServletResponse) externalContext().getResponse();
	}

	
	protected HttpServletRequest getHttpRequest(){
		return (HttpServletRequest) externalContext().getRequest();
	}
	
	@Override
	protected HttpSession getHttpSession(){
		return getHttpRequest().getSession(Boolean.TRUE);
	}
	
	protected void redirecionaPagina(Pagina pagina) throws NegocioException{
		
		String caminhoPagina = getHttpRequest().getContextPath().concat(pagina.getValor());
		
		adicionaMensagensNaSessao();
		
		try {
			externalContext().redirect(caminhoPagina);
		} catch (IOException e) {
			throw new NegocioException(Mensagem.MEI009, e);
		}
	}
	
	private void adicionaMensagensNaSessao() {
		
		if (!context().getMessageList().isEmpty()){
			setAtributoSessao(AtributoSessao.MENSAGEM, context().getMessageList());
		}
	}

	protected void gerarRelatorio(RelatorioEnum relatorio, AbstractParametrosRelatorio parametros) throws NegocioException{
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(getHttpRequest(),getHttpResponse());
		
		relatorioUtil.gerarRelatorio(relatorio, parametros);
		
		context().renderResponse();
		context().responseComplete();
	}
	
	public Object getAtributoSessao(AtributoSessao attSessao){  
        return  getHttpSession().getAttribute(attSessao.name());  
    }  
      
	public void setAtributoSessao(AtributoSessao attSessao, Object value){  
        getHttpSession().setAttribute(attSessao.name(), value);
    }

	public void limparAtributoDaSessao(AtributoSessao name) {
		setAtributoSessao(name, null);
	} 
}