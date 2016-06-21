package br.com.localone.admin.estoque;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarEstoque")
@ViewScoped
public class EstoqueAlterarController extends EstoqueSuperController{
	
	@EJB
	protected GenericServiceController<Estoque, Long> service;
	
	@EJB
	private EstoqueValidacao estoqueValidacao;
	
	@PostConstruct
	public void obtemEstoqueSelecionado() throws NegocioException{
		
		Object dadosEstoque = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_ESTOQUE);
		
		if (dadosEstoque != null && dadosEstoque instanceof Estoque){
			
			filtroEstoqueDTO = new FiltroEstoqueDTO();
			super.estoque = (Estoque) dadosEstoque;
			montaSubMedida();
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar(){
		
		try {
			
			estoqueValidacao.validacao(estoque);
			
			service.salvar(estoque);
			inicio();
			redirecionaPagina(Pagina.PAINEL_ESTOQUE);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_ESTOQUE);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_ESTOQUE;
	}
}