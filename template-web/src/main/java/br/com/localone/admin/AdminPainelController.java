package br.com.localone.admin;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.AutorizacaoEnum;
import br.com.localone.autorizacao.Pagina;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;

@ManagedBean(name="painelAdmin")
@ViewScoped
public class AdminPainelController extends AbstractManageBean{
	
	@PostConstruct
	public void inicio() throws NegocioException, IOException{
		
		if (AutorizacaoEnum.GARCOM.equals(getAutorizacao())){
			
			redirecionaPagina(Pagina.PAINEL_MESA);
			
		}else if (AutorizacaoEnum.COZINHEIRO.equals(getAutorizacao())){
			
			redirecionaPagina(Pagina.PAINEL_COZINHA);
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_ADMIN;
	}
}