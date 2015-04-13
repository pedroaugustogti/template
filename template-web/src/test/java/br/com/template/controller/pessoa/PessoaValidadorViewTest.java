package br.com.template.controller.pessoa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.template.controller.pessoa.PessoaValidadorView;
import br.com.template.domain.Mensagem;
import br.com.template.excecao.NegocioException;

public class PessoaValidadorViewTest {
	
	private static final String MAIS_QUE_20_CARACTERES = "126846545646546545646546546545646546546523423423";
	private static final String MENOS_QUE_20_CARACTERES = "ASDIAJSIDJAS";
	private static final String NAO_INFORMANDO_VALOR = null;
	
	private PessoaValidadorView pessoaValidadorView;
	
	@Before
	public void init(){
		pessoaValidadorView = new PessoaValidadorView();
	}
	
	@Test(expected=NegocioException.class)
	public void verificaValidacaoAcimaLimiteCaracteres() throws NegocioException{
		
		pessoaValidadorView.verificarExcessoCaracteres(MAIS_QUE_20_CARACTERES);
	}
	
	@Test
	public void verificaValidacaoDentroLimiteCaracteres() throws NegocioException{
		
		pessoaValidadorView.verificarExcessoCaracteres(MENOS_QUE_20_CARACTERES);
	}
	
	@Test
	public void verificaValidacaoNull() throws NegocioException{
		
		pessoaValidadorView.verificarExcessoCaracteres(NAO_INFORMANDO_VALOR);
	}
	
	@Test
	public void verificaMensagemValidacaoAcimaLimiteCaracteres(){
		
		try {
			pessoaValidadorView.verificarExcessoCaracteres(MAIS_QUE_20_CARACTERES);
		} catch (NegocioException e) {
			Assert.assertEquals(e.getMensagemEnum(), Mensagem.MNG001);
			Assert.assertEquals(e.getTipo(), Mensagem.MNG001.getTipo());
		}
	}
}
