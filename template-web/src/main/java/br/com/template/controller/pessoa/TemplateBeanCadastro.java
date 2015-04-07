/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller.pessoa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.autorizacao.Pagina;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="templateBeanCadastro")
@ViewScoped	
public class TemplateBeanCadastro extends AbstractManageBean{
	
	@EJB
	protected TemplateValidationView validationView;
	
	@EJB
	protected GenericServiceController<EntidadeExemplo, Long> genericServiceExemplo;
	
	private EntidadeExemplo entidade;
	
	@PostConstruct
	public void init(){
		
		entidade = new EntidadeExemplo();
	}

	public void salvar() throws NegocioException {
		
		validationView.verificarExcessoCaracteres(entidade.getNome());
		genericServiceExemplo.salvar(entidade);
		init();
		
		redirecionaPagina(Pagina.CONSULTAR_PESSOA);
	}
	
	public EntidadeExemplo getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeExemplo entidade) {
		this.entidade = entidade;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_PESSOA;
	}
}