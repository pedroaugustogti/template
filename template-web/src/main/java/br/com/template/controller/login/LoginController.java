package br.com.template.controller.login;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Cargo;
import br.com.template.domain.Role;
import br.com.template.entidades.Balanco;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Pessoa;
import br.com.template.entidades.Usuario;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

/**
 * 
 * @author pedro.oliveira
 * 
 *         <p>
 *         Classe responsável por ser o filtro do primeiro acesso interno dentro
 *         do sistema
 *         </p>
 *         <p>
 *         Ao tentar logar no sistema a ação passa por esse manage bean e é
 *         passado a requisição para o Spring Security realizar a autenticação.
 *         </p>
 * 
 *         <p>
 *         Completando a resposta, o contexto do Spring Security vai enviar a
 *         requisição para o método do autenticador customizado do projeto
 *         AuthenticationProviderCustom.java método authenticate(Authentication)
 *         </p>
 */
@ManagedBean
@RequestScoped
public class LoginController extends AbstractManageBean {

	private static final String SPRING_SECURITY = "/j_spring_security_check";

	// Remover quando banco de dados estiver montado
	@EJB
	private GenericServiceController<Usuario, Long> service;

	@EJB
	private GenericServiceController<Cardapio, Long> serviceCardapio;

	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;

	@EJB
	private GenericServiceController<Balanco, Long> serviceBalanco;

	// Remover método quando banco de dados estiver montado
	@PostConstruct
	public void criarUsuarioAdminAutomaticamente() {

		if (service.listarTodos(Usuario.class).isEmpty()) {

			// Alterar trecho de código
//			 incluiFuncionarioTemporario();

			incluiUsuarioTemporario();

			// incluiCardapio();

			// incluirBalanco();
		}

	}

	private void incluiCardapio() {

		Cardapio cardapio = new Cardapio();
		cardapio.setDescricao("teste");

		serviceCardapio.salvarSemMensagem(cardapio);
	}

	private void incluirBalanco() {

		Balanco balancoMesa = new Balanco();

		Integer numMesa = 1;
		Funcionario func = new Funcionario();
		func.setId(1L);
		Cardapio cardapio = new Cardapio();
		cardapio.setId(1l);

		balancoMesa.setCardapio(cardapio);
		balancoMesa.setGarcom(func);
		balancoMesa.setCozinheiro(func);
		balancoMesa.setHorarioSolicitacao(new Date());
		balancoMesa.setHorarioConclusao(new Date());
		balancoMesa.setNumMesa(numMesa);
		balancoMesa.setValorConta(3000.0);
		balancoMesa.setFechamentoConta(new Date());

		serviceBalanco.salvarSemMensagem(balancoMesa);
	}

	private void incluiFuncionarioTemporario() {

		Funcionario func = new Funcionario();

		func.setCargo(Cargo.COZINHEIRO);
		Pessoa pessoa = new Pessoa();

		pessoa.setNome("Maria José");
		func.setPessoa(pessoa);

		serviceFuncionario.salvar(func);

		Funcionario func1 = new Funcionario();

		func1.setCargo(Cargo.GARCOM);
		Pessoa pessoa1 = new Pessoa();

		pessoa1.setNome("Alfred");
		func1.setPessoa(pessoa1);

		serviceFuncionario.salvar(func1);
	}

	private void incluiUsuarioTemporario() {
		Usuario usuario = new Usuario();
		String[] roleAdmin = { Role.ADMIN.getLabel() };
		usuario.setRoles(Role.getRolesPorLabel(roleAdmin));
		usuario.setSenha("admin");
		usuario.setUsuario("admin");
		service.salvar(usuario);

		Usuario usuario1 = new Usuario();
		usuario1.setRoles(Role.getRolesPorCargo(Cargo.COZINHEIRO));
		usuario1.setSenha("cozinheiro");
		usuario1.setUsuario("cozinheiro");

		Funcionario func = new Funcionario();
		func.setId(1l);
		usuario1.setFuncionario(func);
		service.salvar(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setRoles(Role.getRolesPorCargo(Cargo.GARCOM));
		usuario2.setSenha("garcom");
		usuario2.setUsuario("garcom");

		Funcionario func1 = new Funcionario();
		func1.setId(2l);
		usuario2.setFuncionario(func1);
		service.salvar(usuario2);
	}

	public void doLogin() throws IOException, ServletException {

		RequestDispatcher dispatcher = getHttpRequest().getRequestDispatcher(
				SPRING_SECURITY);

		dispatcher.forward(getHttpRequest(), getHttpResponse());

		context().responseComplete();
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.LOGIN;
	}
}