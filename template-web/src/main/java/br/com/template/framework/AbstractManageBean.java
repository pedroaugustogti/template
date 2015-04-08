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
import br.com.template.util.relatorio.AbstractRelatorioParametro;
import br.com.template.util.relatorio.RelatorioUtil;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Classe Pai de todos os ManagesBean do projeto</p>
 * 
 * <p>Possui métodos auxiliares para facilitar o manuseio do contexto do JSF nos manage Beans</p>
 * 
 * <p>Possui métodos pré-codificados para abstrar a implementação no ManageBean filho Ex.: gerarRelatorio e os métodos de autorização.</p>
 *
 */
public abstract class AbstractManageBean extends AutorizacaoManageBean {
	
	private AutorizacaoEnum autorizacao;
	
	@Override
	public boolean autorizaFuncionalidade(TipoFuncionalidadeEnum tipoFuncionalidade){
		
		return FuncionalidadeEnum.verificaAutorizacaoComAcessoNaFuncionalidade(autorizacao, tipoFuncionalidade);
	}
	
	@PostConstruct
	@Override
	protected void validaPermissionamento() throws IOException, NegocioException {
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	autorizacao = AutorizacaoEnum.usuarioComAcesso(getPaginaManageBean(), permissoesUsuarioLogado());
    	
    	if (autorizacao == null || !authentication.isAuthenticated()) {
    		
    		logout();
        }
	}
	
	/**
	 * Método utilizado para saber qual página do ManageBean filho está acessando para realizar o permissionamento adequado.
	 * 
	 * @return {@link Pagina}
	 */
	protected abstract Pagina getPaginaManageBean();
	
	/**
	 * Redireciona a tela do usuário para a página passada no parametro.
	 * 
	 * @param {@link Pagina} 
	 * @throws NegocioException
	 */
	public void redirecionaPagina(Pagina pagina) throws NegocioException{
		
		String caminhoPagina = getHttpRequest().getContextPath().concat(pagina.getValor());
		
		adicionaMensagensNaSessao();
		
		try {
			externalContext().redirect(caminhoPagina);
		} catch (IOException e) {
			throw new NegocioException(Mensagem.MEI009, e);
		}
	}
	
	/**
	 * Redireciona para tela {@linkPagina.LOGIN} e invalida a sessão do usuário.
	 * 
	 * @throws NegocioException
	 */
	public void logout() throws NegocioException {
		
		redirecionaPagina(Pagina.LOGIN);
		getHttpSession().invalidate();
	}
	
	@Override
	protected HttpSession getHttpSession(){
		return getHttpRequest().getSession(Boolean.TRUE);
	}
	
	/**
	 * <p>Quando uma tela é redirecionada para outra, as mensagens informativas para o usuário podem se perder nessa transição.</p>
	 * 
	 * <p>Nesse caso esse método deve ser acionado atráves da tela JSF no qual você deseja mostrar as mensagens não renderizadas.</p>
	 * 
	 * <p>Exemplo: (Exemplo implementado na pagina consultarPessoa.xhtml)</p>
	 * 
	 * <ul>
	 * 	<li> 
	 * 		f:event listener="#{manageBean.renderizaMensagensDaSessao()}" type="preRenderView"  
	 *  </li>
	 * </ul>
	 * 
	 */
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
	
	/**
	 * 
	 * @return Contexto JSF da instância em execução.
	 */
	protected FacesContext context(){
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * 
	 * @return Contexto Externo JSF da instância em execução.
	 */
	protected ExternalContext externalContext() {
		return context().getExternalContext();
	}
	
	/**
	 * 
	 * @return HttpServletResponse a partir do contexto JSF.
	 */
	protected HttpServletResponse getHttpResponse(){
		return (HttpServletResponse) externalContext().getResponse();
	}

	/**
	 * 
	 * @return HttpServletRequest a partir do contexto JSF.
	 */
	protected HttpServletRequest getHttpRequest(){
		return (HttpServletRequest) externalContext().getRequest();
	}
	
	protected void gerarRelatorio(RelatorioEnum relatorio, AbstractRelatorioParametro parametros) throws NegocioException{
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(getHttpRequest(),getHttpResponse());
		
		relatorioUtil.gerarRelatorio(relatorio, parametros);
		
		context().renderResponse();
		context().responseComplete();
	}
	
	protected Object getAtributoSessao(AtributoSessao attSessao){  
        return  getHttpSession().getAttribute(attSessao.name());  
    }  
      
	protected void setAtributoSessao(AtributoSessao attSessao, Object value){  
        getHttpSession().setAttribute(attSessao.name(), value);
    }

	protected void limparAtributoDaSessao(AtributoSessao name) {
		setAtributoSessao(name, null);
	} 
	
	private void adicionaMensagensNaSessao() {
		
		if (!context().getMessageList().isEmpty()){
			setAtributoSessao(AtributoSessao.MENSAGEM, context().getMessageList());
		}
	}
}