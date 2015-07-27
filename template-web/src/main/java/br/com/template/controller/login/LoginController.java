package br.com.template.controller.login;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import br.com.template.autorizacao.Pagina;
import br.com.template.domain.Role;
import br.com.template.entidades.Usuario;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

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
	
	//Remover quando banco de dados estiver montado
	@EJB
	private GenericServiceController<Usuario, Long> service;
	
	//Remover método quando banco de dados estiver montado
	@PostConstruct
	public void criarUsuarioAdminAutomaticamente(){
		
		if (service.listarTodos(Usuario.class).isEmpty()){
			
			//Alterar trecho de código
			Usuario usuario = new Usuario();
			String[] roleAdmin = {Role.ADMIN.getLabel()};
			usuario.setRoles(Role.getRolesPorLabel(roleAdmin));
			usuario.setSenha("admin");
			usuario.setUsuario("admin");
			service.salvar(usuario);
		}
		
	}
	
    public void doLogin() throws IOException, ServletException {

        RequestDispatcher dispatcher = getHttpRequest().getRequestDispatcher(SPRING_SECURITY);

        dispatcher.forward(getHttpRequest(), getHttpResponse());

        context().responseComplete();
    }

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.LOGIN;
	}
}