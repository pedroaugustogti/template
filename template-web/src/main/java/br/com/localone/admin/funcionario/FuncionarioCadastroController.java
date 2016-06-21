package br.com.localone.admin.funcionario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroFuncionario")
@ViewScoped
public class FuncionarioCadastroController extends FuncionarioSuperController{
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void cadastrar(){
		
		try {
			validacao.validacao(funcionario);
			service.salvar(funcionario);
			inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_FUNCIONARIO;
	}
}