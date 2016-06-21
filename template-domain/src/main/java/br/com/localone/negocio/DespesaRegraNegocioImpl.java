package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.DespesaService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;

@Stateless
public class DespesaRegraNegocioImpl implements DespesaRegraNegocio{
	
	@EJB
	private DespesaService service;

	@Override
	public void proibeCadastroComMesmaDescricao(Despesa despesa) throws NegocioException {
		
		if (despesa.getId() == null){
			
			regraNegocioCasoCadastro(despesa);
		}else{
			
			regraNegocioCasoAlteracao(despesa);
		}
	}

	private void regraNegocioCasoAlteracao(Despesa despesa) throws NegocioException {
		
		List<Despesa> listDespesa = despesaPorDescricao(despesa);
		
		if (!listDespesa.isEmpty()){
			
			Despesa despesaBancoDados = listDespesa.iterator().next();
			
			if (!despesa.getId().equals(despesaBancoDados.getId())){
				
				throw new NegocioException(Mensagem.MNG016);
			}
		}
	}

	private void regraNegocioCasoCadastro(Despesa despesa) throws NegocioException {
		
		List<Despesa> listDespesa = despesaPorDescricao(despesa);
		
		if (!listDespesa.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG016);
		}
	}

	private List<Despesa> despesaPorDescricao(Despesa despesa) {
		
		FiltroDespesaDTO filtro = new FiltroDespesaDTO();
		
		filtro.setDescricaoExata(despesa.getDescricao());
		
		List<Despesa> listDespesa = service.pesquisar(filtro);
		return listDespesa;
	}
}