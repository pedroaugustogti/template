package br.com.template.controller.login;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import br.com.template.autorizacao.Pagina;
import br.com.template.framework.AbstractManageBean;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Classe responsável por ser o filtro do primeiro acesso interno dentro do sistema</p>
 * <p>Ao tentar logar no sistema a ação passa por esse manage bean e é passado a requisição para o Spring Security realizar a autenticação.</p>
 * 
 * <p>Completando a resposta, o contexto do Spring Security vai enviar a requisição para o método do autenticador customizado do projeto AuthenticationProviderCustom.java método authenticate(Authentication)</p>
 */
@ManagedBean
@RequestScoped
public class LoginController extends AbstractManageBean{
	
	private static final String SPRING_SECURITY = "/j_spring_security_check";
	
    public void doLogin() throws IOException, ServletException {

        RequestDispatcher dispatcher = getHttpRequest().getRequestDispatcher(SPRING_SECURITY);

        dispatcher.forward(getHttpRequest(), getHttpResponse());

        FacesContext.getCurrentInstance().responseComplete();
    }

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.LOGIN;
	}
}