package br.com.localone.admin.despesa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="cadastroDespesa")
@ViewScoped
public class DespesaCadastroController extends AbstractManageBean{
	
	private Despesa despesa;
	
	@EJB
	private GenericServiceController<Despesa, Long> service;
	
	@EJB
	private DespesaValidacao despesaValidacao;
	
	@PostConstruct
	public void inicio(){
		
		despesa = new Despesa();
	}
	
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

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
}