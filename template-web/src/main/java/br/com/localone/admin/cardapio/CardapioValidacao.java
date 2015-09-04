package br.com.localone.admin.cardapio;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.CardapioRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioTempoPreparo;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class CardapioValidacao extends AbstractValidacao{
	
	@EJB
	private CardapioRegraNegocio cardapioRegraNegocio;

	public void valida(Cardapio cardapio) throws NegocioException{
		
		campoObrigatorio(cardapio);
		proibeCadastroCardapioComMesmaDescricao(cardapio);
	}

	private void proibeCadastroCardapioComMesmaDescricao(Cardapio cardapio) throws NegocioException {
		
		cardapioRegraNegocio.proibeCadastroComMesmaDescricao(cardapio);
	}

	private void campoObrigatorio(Cardapio cardapio) throws NegocioException {
		
		if (cardapio.getTipoProduto() == null){
			throw new NegocioException(Mensagem.MNG008);
		}
		
		if (StringUtils.isBlank(cardapio.getDescricao())){
			throw new NegocioException(Mensagem.MNG023);
		}
		
		if (cardapio.getTempoPreparo() == null || tempoPreparoObrigatorio(cardapio.getTempoPreparo())){
			throw new NegocioException(Mensagem.MNG021);
		}
		
		if (listNaoInformado(cardapio.getListIngredientes())){
			
			throw new NegocioException(Mensagem.MNG022);
		}
		
		if (decimalNaoInformado(cardapio.getPreco())){
			
			throw new NegocioException(Mensagem.MNG012);
		}
	}

	private boolean tempoPreparoObrigatorio(CardapioTempoPreparo tempoPreparo) {
		
		return inteiroNaoInformado(tempoPreparo.getHora()) &&
				inteiroNaoInformado(tempoPreparo.getMinuto()) &&
				inteiroNaoInformado(tempoPreparo.getSegundo());
	}
}