package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.CardapioService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.excecao.NegocioException;

@Stateless
public class CardapioRegraNegocioImpl implements CardapioRegraNegocio{
	
	@EJB
	private CardapioService service;

	@Override
	public void proibeCadastroComMesmaDescricao(Cardapio cardapio) throws NegocioException {
		
		if (cardapio.getId() == null){
			
			regraNegocioCasoCadastro(cardapio);
		}else{
			
			regraNegocioCasoAlteracao(cardapio);
		}
	}

	private void regraNegocioCasoAlteracao(Cardapio cardapio) throws NegocioException {
		
		List<Cardapio> listCardapio = cardapioPorDescricao(cardapio);
		
		if (!listCardapio.isEmpty()){
			
			Cardapio cardapioBancoDados = listCardapio.iterator().next();
			
			if (!cardapio.getId().equals(cardapioBancoDados.getId())){
				
				throw new NegocioException(Mensagem.MNG016);
			}
		}
	}

	private void regraNegocioCasoCadastro(Cardapio cardapio) throws NegocioException {
		
		List<Cardapio> listCardapio = cardapioPorDescricao(cardapio);
		
		if (!listCardapio.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG016);
		}
	}

	private List<Cardapio> cardapioPorDescricao(Cardapio cardapio) {
		
		FiltroCardapioDTO filtro = new FiltroCardapioDTO();
		
		filtro.setDescricaoExata(cardapio.getDescricao());
		
		List<Cardapio> list = service.pesquisar(filtro);
		
		return list;
	}
}