package br.com.localone.admin.funcionario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Funcionario;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarFuncionario")
@ViewScoped
public class FuncionarioAlterarController extends FuncionarioSuperController{
	
	@PostConstruct
	public void setFuncionarioPorSessao() throws NegocioException{
		
		Object dadosFuncionario = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_FUNCIONARIO);
		
		if (dadosFuncionario != null && dadosFuncionario instanceof Funcionario){
			
			super.inicio();
			funcionario = (Funcionario) dadosFuncionario;
			montaCidadesPorEstado();
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar() {
		
		try {
			validacao.validacao(funcionario);
			service.salvar(funcionario);
			inicio();
			redirecionaPagina(Pagina.PAINEL_FUNCIONARIO);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_FUNCIONARIO);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_FUNCIONARIO;
	}
}