/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller.pessoa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.autorizacao.Pagina;
import br.com.template.entidades.Pessoa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="templateBeanCadastro")
@ViewScoped	
public class PessoaControllerCadastra extends AbstractManageBean{
	
	@EJB
	protected PessoaValidadorView validationView;
	
	@EJB
	protected GenericServiceController<Pessoa, Long> genericServiceExemplo;
	
	private Pessoa entidade;
	
	@PostConstruct
	public void init(){
		
		entidade = new Pessoa();
	}

	public void salvar() throws NegocioException {
		
		validationView.verificarExcessoCaracteres(entidade.getNome());
		genericServiceExemplo.salvar(entidade);
		init();
		
		redirecionaPagina(Pagina.CONSULTAR_PESSOA);
	}
	
	public Pessoa getEntidade() {
		return entidade;
	}

	public void setEntidade(Pessoa entidade) {
		this.entidade = entidade;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_PESSOA;
	}
}