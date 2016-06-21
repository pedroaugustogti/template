package br.com.localone.admin.funcionario.salario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.admin.funcionario.FuncionarioSuperController;
import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.FuncionarioService;
import br.com.template.entidades.Funcionario;

@ManagedBean(name="painelFuncionarioSalario")
@ViewScoped
public class FuncionarioPainelSalarioController extends FuncionarioSuperController{
	
	private List<Funcionario> listFuncionario;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void pesquisar(){
		
		listFuncionario = funcionarioService.pesquisar(filtroFuncionarioDTO);
		
//		calculaFaturamento
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_FUNCIONARIO;
	}

	public List<Funcionario> getListFuncionario() {
		return listFuncionario;
	}
}