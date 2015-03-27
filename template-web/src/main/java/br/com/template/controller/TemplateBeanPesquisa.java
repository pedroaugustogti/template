package br.com.template.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.controller.service.GenericServiceController;
import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.dto.FiltroEntidadeExemploDTO;
import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.navegacao.Pagina;
import br.com.template.service.TemplateService;

@ManagedBean(name="templateBeanPesquisa")
@ViewScoped
public class TemplateBeanPesquisa extends AbstractManageBean{
	

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
	
	public void gerarRelatorio() throws NegocioException {
		
		TemplateBeanParametroRelatorio parametroRelatorio = new TemplateBeanParametroRelatorio(entidades);
		
		super.gerarRelatorio(RelatorioEnum.TEMPLATE, parametroRelatorio);
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

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CONSULTAR_PESSOA;
	}
}