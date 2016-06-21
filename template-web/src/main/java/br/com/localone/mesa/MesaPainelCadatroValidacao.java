package br.com.localone.mesa;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.localone.negocio.MesaRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Mesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class MesaPainelCadatroValidacao extends AbstractValidacao{
	
	@EJB
	private MesaRegraNegocio regraNegocio;
	
	public void validacao(Mesa mesa) throws NegocioException {
		
		if (mesa == null || inteiroNaoInformado(mesa.getNumMesa())){
			
			throw new NegocioException(Mensagem.MNG026);
		}
		
		regraNegocio.proibeCadastroComMesmoNumero(mesa);
	}
}