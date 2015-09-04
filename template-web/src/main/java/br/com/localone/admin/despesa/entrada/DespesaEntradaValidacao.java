package br.com.localone.admin.despesa.entrada;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.domain.Mensagem;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class DespesaEntradaValidacao extends AbstractValidacao{
	
	public void validacao(DespesaEntrada despesaEntrada) throws NegocioException {
		
		camposObrigatorios(despesaEntrada);
	}

	private void camposObrigatorios(DespesaEntrada despesaEntrada) throws NegocioException {
		
		if (despesaEntrada.getDespesa().getId() == null){
			
			throw new NegocioException(Mensagem.MNG028);
		}
		
		if (decimalNaoInformado(despesaEntrada.getPreco())){
			throw new NegocioException(Mensagem.MNG012);
		}
		
		if (despesaEntrada.getData() == null){
			throw new NegocioException(Mensagem.MNG029);
		}
	}
}