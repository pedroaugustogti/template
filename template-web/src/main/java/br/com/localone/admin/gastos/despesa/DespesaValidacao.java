package br.com.localone.admin.gastos.despesa;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.DespesaRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Despesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class DespesaValidacao {
	
	@EJB
	private DespesaRegraNegocio regraNegocio;
	
	public void validacao(Despesa despesa) throws NegocioException {
		
		camposObrigatorios(despesa);
		
		proibeCadastroComMesmaDescricao(despesa);
	}

	private void proibeCadastroComMesmaDescricao(Despesa despesa) throws NegocioException {
		
		regraNegocio.proibeCadastroComMesmaDescricao(despesa);
	}

	private void camposObrigatorios(Despesa despesa) throws NegocioException {
		
		if (despesa.getEmpresa() == null){
			
			throw new NegocioException(Mensagem.MNG008);
		}
		
		if (StringUtils.isBlank(despesa.getDescricao())){
			throw new NegocioException(Mensagem.MNG028);
		}
	}
}