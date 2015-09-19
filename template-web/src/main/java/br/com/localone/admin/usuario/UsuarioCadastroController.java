package br.com.localone.admin.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Role;
import br.com.template.dto.FiltroUsuarioDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Pessoa;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroUsuario")
@ViewScoped
public class UsuarioCadastroController extends UsuarioSuperController{
	
	private boolean usuarioAdmin;

	@PostConstruct
	public void inicio() throws NegocioException{
		
		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();
		Funcionario funcionario = new Funcionario();
		filtroUsuarioDTO = new FiltroUsuarioDTO();
		
		funcionario.setPessoa(pessoa);
		usuario.setFuncionario(funcionario);
		
		usuario.setPessoa(pessoa);
		
		setUsuario(usuario);
	}
	
	public void identificaFuncionarioOuAdministrador(){
		
		usuarioAdmin = Role.ADMIN.equals(getUsuario().getRole());
	}
	
	public void cadastrar()  {
		
		try {
			
			verificaFuncionarioSelecionado();
			validacaoUsuario.confirmaSenha(getUsuario(), confirmarSenha);
			super.cadastrar();
			this.inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	};
	
	/**
	 * Elimina referência do objeto funcionario dentro da entidade Usuario,
	 * assim evita a exceção org.hibernate.TransientPropertyValueException
	 */
	private void verificaFuncionarioSelecionado() {
		
		if (getUsuario().getFuncionario().getId() == null){
			
			getUsuario().setFuncionario(null);
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_USUARIO;
	}

	public boolean getUsuarioAdmin() {
		return usuarioAdmin;
	}
}