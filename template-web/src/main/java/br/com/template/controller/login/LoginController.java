package br.com.template.controller.login;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import br.com.template.autorizacao.Pagina;
import br.com.template.framework.AbstractManageBean;

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