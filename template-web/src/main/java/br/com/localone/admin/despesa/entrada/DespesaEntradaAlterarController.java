package br.com.localone.admin.despesa.entrada;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroDespesaEntradaDTO;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarDespesaEntrada")
@ViewScoped
public class DespesaEntradaAlterarController extends DespesaEntradaSuperController{
	
	@EJB
	protected GenericServiceController<DespesaEntrada, Long> service;
	
	@EJB
	private DespesaEntradaValidacao despesaEntradaValidacao;
	
	@PostConstruct
	public void obtemDespesaEntradaSelecionado() throws NegocioException{
		
		Object dadosDespesaEntrada = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_DESPESA_ENTRADA);
		
		if (dadosDespesaEntrada != null && dadosDespesaEntrada instanceof DespesaEntrada){
			
			filtroDespesaEntradaDTO = new FiltroDespesaEntradaDTO();
			super.despesaEntrada = (DespesaEntrada) dadosDespesaEntrada;
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}
	
	public void alterar(){
		
		try {
			
			despesaEntradaValidacao.validacao(despesaEntrada);
			
			service.salvar(despesaEntrada);
			inicio();
			redirecionaPagina(Pagina.PAINEL_DESPESA_ENTRADA);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_DESPESA_ENTRADA);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_DESPESA_ENTRADA;
	}
}