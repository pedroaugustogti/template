package br.com.localone.admin.gastos.receita;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.template.domain.Mensagem;
import br.com.template.entidades.Bem;
import br.com.template.entidades.Receita;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class ReceitaValidadorView extends AbstractValidacao{
	
	public void valida(Receita receita) {
		
	}
	
	public void validaVendaBem (Bem bem) throws NegocioException{
		
		if (bem.getValorVendido() == null){
			
			throw new NegocioException(Mensagem.MNG043);
		}
	}
	
	public void validaAdicionarBem (Bem bem) throws NegocioException{
		
		if (StringUtils.isBlank(bem.getDescricao())){
			
			throw new NegocioException(Mensagem.MNG044);
		}
		
		if (decimalNaoInformado(bem.getValor())){
			
			throw new NegocioException(Mensagem.MNG045);
		}
	}
}