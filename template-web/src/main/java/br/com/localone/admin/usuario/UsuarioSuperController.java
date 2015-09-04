package br.com.localone.admin.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import br.com.localone.service.FuncionarioService;
import br.com.template.domain.Role;
import br.com.template.domain.Situacao;
import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class UsuarioSuperController extends AbstractManageBean{
	
	private Usuario usuario;
	
	@EJB
	private GenericServiceController<Usuario, Long> service;
	
	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	protected UsuarioValidadorView validacaoUsuario;
	
	protected String confirmarSenha;
	
	public List<SelectItem> getSelectItemsUsuario(){
		
		List<SelectItem> listSelectItems = new ArrayList<SelectItem>();
		List<Funcionario> listFuncionarios = funcionarioService.pesquisar(getFiltroFuncionario());
		
		for(Funcionario funcionario : listFuncionarios){
			
			SelectItem selectItem = new SelectItem();
			
			selectItem.setLabel(funcionario.getPessoa().getNome());
			selectItem.setValue(funcionario.getId());
			
			listSelectItems.add(selectItem);
		}
		
		return listSelectItems;
	}
	
	public void carregaFuncionario (){
		
		if (usuario.getFuncionario().getId() != null){
			
			usuario.setFuncionario(serviceFuncionario.getById(Funcionario.class, usuario.getFuncionario().getId()));
		}else{
			usuario.setFuncionario(new Funcionario());
		}
	}
	
	protected void cadastrar() throws NegocioException{
		
		validacaoUsuario.confirmaSenha(usuario, getConfirmarSenha());
		
		usuario.setRoles(Role.getRolesPorCargo(usuario.getFuncionario().getCargo()));
		
		service.salvar(usuario);
	}
	
	private FiltroFuncionarioDTO getFiltroFuncionario() {
		
		FiltroFuncionarioDTO filtro = new FiltroFuncionarioDTO();
		
		filtro.setSituacao(Situacao.ATIVO);
		
		return filtro;
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
}