package br.com.localone.admin.funcionario;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.FuncionarioService;
import br.com.template.domain.Situacao;
import br.com.template.entidades.Funcionario;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelFuncionario")
@ViewScoped
public class FuncionarioPainelController extends FuncionarioSuperController{
	
	private List<Funcionario> listFuncionario;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void pesquisar(){
		
		listFuncionario = funcionarioService.pesquisar(filtroFuncionarioDTO);
	}
	
	public void redirecionaParaTelaAlterar(Funcionario funcionario) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_FUNCIONARIO, funcionario);
		redirecionaPagina(Pagina.ALTERAR_FUNCIONARIO);
	}
	
	public void excluir(){
		service.excluir(funcionario);
		this.pesquisar();
	}
	
	public void ativarFuncionario(Funcionario funcionario){
		
		funcionario.setSituacao(Situacao.ATIVO);
		service.salvar(funcionario);
	}
	
	public void inativarFuncionario(Funcionario funcionario){
		
		funcionario.setSituacao(Situacao.INATIVO);
		service.salvar(funcionario);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_FUNCIONARIO;
	}

	public List<Funcionario> getListFuncionario() {
		return listFuncionario;
	}
}