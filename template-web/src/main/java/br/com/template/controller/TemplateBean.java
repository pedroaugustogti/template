/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.controller.service.GenericServiceController;
import br.com.template.controller.validation.view.TemplateValidationView;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.service.TemplateService;

@ManagedBean(name="templateBean")
@ViewScoped
public class TemplateBean {
	
	@EJB
	private TemplateValidationView validationView;
	
	@EJB
	private GenericServiceController<EntidadeExemplo, Long> genericServiceExemplo;
	
	@EJB
	private TemplateService templateService;
	
	private EntidadeExemplo entidade;
	
	private List<EntidadeExemplo> entidades;
	
	@PostConstruct
	public void init(){
		
		entidade = new EntidadeExemplo();
	}
	
	public void pesquisar() throws NegocioException{
		
		validationView.verificarDigitosSuperiorTresCaracteres(entidade.getDescricao());
		entidades = genericServiceExemplo.listarTodos(EntidadeExemplo.class);
	}
	
	public void salvar() throws NegocioException {
		
		validationView.verificarDigitosSuperiorTresCaracteres(entidade.getDescricao());
		genericServiceExemplo.salvar(entidade);
		
		init();
	}

	public EntidadeExemplo getEntidade() {
		return entidade;
	}

	public List<EntidadeExemplo> getEntidades() {
		return entidades;
	}
}
