package br.com.template.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.template.controller.login.AtributoSessao;
import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.excecao.NegocioException;
import br.com.template.navegacao.AutorizacaoEnum;
import br.com.template.navegacao.Pagina;
import br.com.template.util.criptografia.CriptografiaUtil;
import br.com.template.util.relatorio.AbstractParametrosRelatorio;
import br.com.template.util.relatorio.RelatorioUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AbstractManageBean extends AutorizacaoManageBean {
	
	@PostConstruct
	@Override
	protected void validaPermissionamento() throws IOException {
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	List<SimpleGrantedAuthority> permissoes = permissoesUsuarioLogado();

    	if (!AutorizacaoEnum.usuarioComAcesso(getPaginaManageBean(), permissoes) || !authentication.isAuthenticated()) {
    		
    		String paginaLogin = getHttpRequest().getContextPath().concat(Pagina.LOGIN.getValor());
    		
    		externalContext().redirect(paginaLogin);
    		
    		getHttpRequest().getSession().invalidate();
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
	
	protected HttpSession getHttpSession(){
		return getHttpRequest().getSession(Boolean.TRUE);
	}
	
	protected void gerarRelatorio(RelatorioEnum relatorio, AbstractParametrosRelatorio parametros) throws NegocioException{
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(getHttpRequest(),getHttpResponse());
		
		relatorioUtil.gerarRelatorio(relatorio, parametros);
		
		context().renderResponse();
		context().responseComplete();
	}
	
	private List<SimpleGrantedAuthority> permissoesUsuarioLogado() {
    	
    	List<SimpleGrantedAuthority> permissoesUsuarioLogado = null;
    	Object atributoSessaoPermissoes = getHttpSession().getAttribute(AtributoSessao.PERMISSOES_USUARIO.getChave());
    	
    	if (atributoSessaoPermissoes != null){
    		
    		String permissoesCriptografada = atributoSessaoPermissoes.toString();
    		
    		String permissoesDescriptografada = CriptografiaUtil.descriptografar(permissoesCriptografada);
        	
    		permissoesUsuarioLogado = new Gson().fromJson(permissoesDescriptografada, new TypeToken<List<SimpleGrantedAuthority>>(){}.getType());
    	}
    	
    	return permissoesUsuarioLogado;
	}
}