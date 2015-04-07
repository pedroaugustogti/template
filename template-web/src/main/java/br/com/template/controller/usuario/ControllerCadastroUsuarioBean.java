package br.com.template.controller.usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.autorizacao.Pagina;
import br.com.template.domain.Role;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean
@ViewScoped
public class ControllerCadastroUsuarioBean extends AbstractManageBean{
	
	@EJB
	private GenericServiceController<Usuario, Long> service;
	
	private Usuario usuario;
	
	private String confirmarSenha;
	
	private List<String> roles;
	private String[] rolesSelecionadas;
	
	@EJB
	private ControllerCadastroUsuarioValidationView validationView;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		roles = Role.getLabelRoles();
	}

    public void salvar() {
    	
    	try {
    		
			validationView.confirmaSenha(usuario, confirmarSenha);
			
			usuario.setRoles(Role.getRolesPorLabel(rolesSelecionadas));
	    	service.salvar(usuario);
	    	init();
	    	
		} catch (NegocioException e) {
			e.printStackTrace();
		}
    }
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String[] getRolesSelecionadas() {
		return rolesSelecionadas;
	}

	public void setRolesSelecionadas(String[] rolesSelecionadas) {
		this.rolesSelecionadas = rolesSelecionadas;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_USUARIO;
	}
}