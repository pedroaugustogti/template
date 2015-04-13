/**
 * Disclaimer: this code is only for demo no production use
 */
package br.com.template.controller.pessoa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.template.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Pessoa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="templateBeanAlterar")
@ViewScoped	
public class PessoaControllerAltera extends AbstractManageBean{
	
	@EJB
	protected PessoaValidadorView validationView;
	
	@EJB
	protected GenericServiceController<Pessoa, Long> genericServiceExemplo;
	
	private Pessoa entidade;

	@PostConstruct
	public void init() throws NegocioException{
		
		Object entidadeParaEditar = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_PESSOA);
		
		if (entidadeParaEditar != null && entidadeParaEditar instanceof Pessoa){
			
			entidade = (Pessoa) entidadeParaEditar;
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar() throws NegocioException{
		
		validationView.verificarExcessoCaracteres(entidade.getNome());
		genericServiceExemplo.salvar(entidade);
		init();
		
		redirecionaPagina(Pagina.CONSULTAR_PESSOA);
		
		limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_PESSOA);
	}
	
	public Pessoa getEntidade() {
		return entidade;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_PESSOA;
	}
}