/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.controller.validation.view.TemplateValidationView;
import br.com.template.excecao.NegocioException;
import br.com.template.repositories.entidades.EntidadeExemplo;

@ManagedBean(name="templateBean")
@ViewScoped
public class TemplateBean {
	
	@EJB
	private TemplateValidationView validationView;

	private EntidadeExemplo entidade;
	
	@PostConstruct
	public void init(){
		
		entidade = new EntidadeExemplo();
	}
	
	public void pesquisar() throws NegocioException{
		
		validationView.verificarDigitosSuperiorTresCaracteres("teste");
		
	}

	public EntidadeExemplo getEntidade() {
		return entidade;
	}
}
