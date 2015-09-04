package br.com.localone.admin.despesa.entrada;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ProdutoService;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="cadastroDespesaEntrada")
@ViewScoped
public class DespesaEntradaCadastroController extends DespesaEntradaSuperController{
	
	@EJB
	private GenericServiceController<DespesaEntrada, Long> service;
	
	@EJB
	private ProdutoService produtoService;
	
	@EJB
	private DespesaEntradaValidacao despesaEntradaValidacao;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
	}
	
	public void cadastrar(){
		
		try{	
			
			despesaEntradaValidacao.validacao(despesaEntrada);
			service.salvar(despesaEntrada);
			inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_DESPESA_ENTRADA;
	}
}