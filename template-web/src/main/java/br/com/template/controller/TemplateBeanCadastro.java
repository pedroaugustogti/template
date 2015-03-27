/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.controller.service.GenericServiceController;
import br.com.template.controller.validation.view.TemplateValidationView;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.navegacao.Pagina;
import br.com.template.service.TemplateService;

@ManagedBean(name="templateBeanCadastro")
@ViewScoped	
public class TemplateBeanCadastro extends AbstractManageBean{
	
	@EJB
	private TemplateValidationView validationView;
	
	@EJB
	private GenericServiceController<EntidadeExemplo, Long> genericServiceExemplo;
	
	@EJB
	private TemplateService templateService;
	
	private EntidadeExemplo entidade;
	
	@PostConstruct
	public void init(){
		entidade = new EntidadeExemplo();
	}

	public void salvar() throws NegocioException {
		
		validationView.verificarExcessoCaracteres(entidade.getNome());
		genericServiceExemplo.salvar(entidade);
		
		init();
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