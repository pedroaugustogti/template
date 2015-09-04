package br.com.localone.admin.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroUsuario")
@ViewScoped
public class UsuarioCadastroController extends UsuarioSuperController{
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		Usuario usuario = new Usuario();
		usuario.setFuncionario(new Funcionario());
		
		setUsuario(usuario);
	}
	
	public void cadastrar()  {
		
		try {
			validacaoUsuario.confirmaSenha(getUsuario(), confirmarSenha);
			super.cadastrar();
			this.inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	};
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_USUARIO;
	}
}