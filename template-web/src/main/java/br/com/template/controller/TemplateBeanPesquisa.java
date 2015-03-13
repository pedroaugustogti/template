/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.controller.service.GenericServiceController;
import br.com.template.dto.FiltroEntidadeExemploDTO;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.service.TemplateService;

@ManagedBean(name="templateBeanPesquisa")
@ViewScoped
public class TemplateBeanPesquisa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2387137209850478788L;

	@EJB
	private GenericServiceController<EntidadeExemplo, Long> genericServiceExemplo;
	
	@EJB
	private TemplateService templateService;
	
	private EntidadeExemplo entidadeSelecionada;
	
	private FiltroEntidadeExemploDTO filtroEntidadeExemploDTO;
	
	private List<EntidadeExemplo> entidades;
	
	@PostConstruct
	public void init(){
		
		filtroEntidadeExemploDTO = new FiltroEntidadeExemploDTO();
	}
	
	public void pesquisar(){
		
		entidades = templateService.pesquisar(filtroEntidadeExemploDTO);
	}
	
	public void excluir(){
		genericServiceExemplo.excluir(entidadeSelecionada);
		this.pesquisar();
	}

	public FiltroEntidadeExemploDTO getFiltroEntidadeExemploDTO() {
		return filtroEntidadeExemploDTO;
	}

	public EntidadeExemplo getEntidadeSelecionada() {
		return entidadeSelecionada;
	}

	public void setEntidadeSelecionada(EntidadeExemplo entidadeSelecionada) {
		this.entidadeSelecionada = entidadeSelecionada;
	}

	public List<EntidadeExemplo> getEntidades() {
		return entidades;
	}
}