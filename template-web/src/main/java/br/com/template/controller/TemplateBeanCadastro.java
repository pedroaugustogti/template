/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.template.controller.service.GenericServiceController;
import br.com.template.controller.validation.view.TemplateValidationView;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.service.TemplateService;

@ManagedBean(name="templateBeanCadastro")
@SessionScoped	
public class TemplateBeanCadastro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2387137209850478788L;

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
}