package br.com.localone.admin.despesa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarDespesa")
@ViewScoped
public class DespesaAlterarController extends AbstractManageBean{
	
	@EJB
	protected GenericServiceController<Despesa, Long> service;
	
	private Despesa despesa;

	@EJB
	private DespesaValidacao despesaValidacao;

	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object dadosDespesa = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_DESPESA);
		
		if (dadosDespesa != null && dadosDespesa instanceof Despesa){
			
			despesa = (Despesa) dadosDespesa;
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar() throws NegocioException{
		
		try {
			
			despesaValidacao.validacao(despesa);
			service.salvar(despesa);
			
			inicio();
			
			redirecionaPagina(Pagina.PAINEL_DESPESA);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_DESPESA);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public Despesa getDespesa() {
		return despesa;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_DESPESA;
	}
}