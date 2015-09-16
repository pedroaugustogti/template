package br.com.localone.admin.despesa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroDespesa")
@ViewScoped
public class DespesaCadastroController extends DespesaSuperController{
	
	public void cadastrar(){
		
		try {
			despesaValidacao.validacao(despesa);
			service.salvar(despesa);
			inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_DESPESA;
	}
}