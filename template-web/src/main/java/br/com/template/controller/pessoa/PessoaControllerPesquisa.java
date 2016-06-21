package br.com.template.controller.pessoa;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.relatorio.RelatorioEnum;
import br.com.template.dto.FiltroEntidadeExemploDTO;
import br.com.template.entidades.Pessoa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.service.TemplateService;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="templateBeanPesquisa")
@ViewScoped
public class PessoaControllerPesquisa extends AbstractManageBean{
	

	@EJB
	private GenericServiceController<Pessoa, Long> genericServiceExemplo;
	
	@EJB
	private TemplateService templateService;
	
	private Pessoa entidadeSelecionada;
	
	private FiltroEntidadeExemploDTO filtroEntidadeExemploDTO;
	
	private List<Pessoa> entidades;
	
	@PostConstruct
	public void init(){
		
		filtroEntidadeExemploDTO = new FiltroEntidadeExemploDTO();
	}
	
	public void gerarRelatorio() throws NegocioException {
		
		PessoaRelatorioParametro parametroRelatorio = new PessoaRelatorioParametro(entidades);
		
		super.gerarRelatorio(RelatorioEnum.BALANCO_GASTO, parametroRelatorio);
	}
	
	public void pesquisar(){
		
		entidades = templateService.pesquisar(filtroEntidadeExemploDTO);
	}
	
	public void redirecionaParaTelaAlterar(Pessoa pessoa) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_PESSOA, pessoa);
		
		redirecionaPagina(Pagina.ALTERAR_PESSOA);
	}
	
	public void excluir(){
		genericServiceExemplo.excluir(entidadeSelecionada);
		this.pesquisar();
	}
	
	public FiltroEntidadeExemploDTO getFiltroEntidadeExemploDTO() {
		return filtroEntidadeExemploDTO;
	}

	public Pessoa getEntidadeSelecionada() {
		return entidadeSelecionada;
	}

	public void setEntidadeSelecionada(Pessoa entidadeSelecionada) {
		this.entidadeSelecionada = entidadeSelecionada;
	}

	public List<Pessoa> getEntidades() {
		return entidades;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CONSULTAR_PESSOA;
	}
}