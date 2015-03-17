package br.com.template.controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.excecao.NegocioException;
import br.com.template.util.relatorio.AbstractParametrosRelatorio;
import br.com.template.util.relatorio.RelatorioUtil;

public abstract class AbstractManageBean {
	
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
	
	protected void gerarRelatorio(RelatorioEnum relatorio, AbstractParametrosRelatorio parametros) throws NegocioException{
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(getHttpRequest(),getHttpResponse());
		
		relatorioUtil.gerarRelatorio(relatorio, parametros);
		
		context().renderResponse();
		context().responseComplete();
	}
}