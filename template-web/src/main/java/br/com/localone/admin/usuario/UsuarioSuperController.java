package br.com.localone.admin.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import br.com.localone.service.FuncionarioService;
import br.com.template.domain.Cargo;
import br.com.template.domain.Role;
import br.com.template.dto.FiltroUsuarioDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.criptografia.CriptografiaUtil;

public abstract class UsuarioSuperController extends AbstractManageBean{
	
	private Usuario usuario;
	
	@EJB
	protected GenericServiceController<Usuario, Long> service;
	
	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	protected UsuarioValidadorView validacaoUsuario;
	
	protected FiltroUsuarioDTO filtroUsuarioDTO;
	
	protected String confirmarSenha;
	
	public List<SelectItem> carregaRoles(){
		
		List<SelectItem> listRoles = new ArrayList<>();
		
		for (Role role : Role.values()){
			
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(role.getLabel());
			selectItem.setValue(role);
			
			listRoles.add(selectItem);
		}
		
		return listRoles;
	}
	
	protected void cadastrar() throws NegocioException{
		
		validacaoUsuario.confirmaSenha(usuario, getConfirmarSenha());
		
		String senhaCriptografada = CriptografiaUtil.criptografar(usuario.getSenha());
		
		usuario.setSenha(senhaCriptografada);
		
		service.salvar(usuario);
	}
	
	public void selecionaFuncionario(Funcionario funcionario){
		
		usuario.setFuncionario(funcionario);
	}
	
	public boolean getRoleParaFuncionario(){
		
		return cargoFuncionarioPorRoleUsuario() != null;
	}
	
	public Cargo cargoFuncionarioPorRoleUsuario(){
		
		return Cargo.cargoPorRoleUsuario(usuario.getRole());
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
	
	public FiltroUsuarioDTO getFiltroUsuarioDTO() {
		return filtroUsuarioDTO;
	}

	public void setFiltroUsuarioDTO(FiltroUsuarioDTO filtroUsuarioDTO) {
		this.filtroUsuarioDTO = filtroUsuarioDTO;
	}
}