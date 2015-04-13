package br.com.template.controller.usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.template.domain.Mensagem;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;

public class UsuarioValidadorViewTest {
	
	private static final String SENHA_INFORMADA = "123";
	private static final String SENHA_CONFIRMACAO_CORRETA = "123";
	private static final String SENHA_CONFIRMACAO_INCORRETA = "abc";
	
	private static final Usuario USUARIO_NAO_INFORMADO = null;
	private static final String CONFIRMACAO_SENHA_NAO_INFORMADO = null;
	
	private UsuarioValidadorView validador;
	private Usuario usuario;
	
	enum TipoInitUsuario{
		
		USUARIO_INFORMANDO_SENHA,
		USUARIO_NAO_INFORMANDO_SENHA;
	}
	
	@Before
	public void init(){
		validador = new UsuarioValidadorView();
		usuario = initUsuario(TipoInitUsuario.USUARIO_INFORMANDO_SENHA);
	}
	
	private Usuario initUsuario(TipoInitUsuario modoInicializacao) {
		
		usuario = new Usuario();
		
		if (TipoInitUsuario.USUARIO_INFORMANDO_SENHA.equals(modoInicializacao)){
			
			usuario.setSenha(SENHA_INFORMADA);
			
		}else {
			
			usuario.setSenha(null);
		}
		
		return usuario;
	}

	@Test
	public void verificaConfirmacaoSenhaCorreta() throws NegocioException{
		
		validador.confirmaSenha(usuario, SENHA_CONFIRMACAO_CORRETA);
	}
	
	@Test(expected=NegocioException.class)
	public void verificaConfirmacaoSenhaIncorreta() throws NegocioException{
		
		validador.confirmaSenha(usuario, SENHA_CONFIRMACAO_INCORRETA);
	}
	
	@Test
	public void verificaMensagemConfirmacaoSenhaIncorreta(){
		
		try {
			validador.confirmaSenha(usuario, SENHA_CONFIRMACAO_INCORRETA);
		} catch (NegocioException e) {
			Assert.assertEquals(e.getMensagemEnum(), Mensagem.MNG005);
			Assert.assertEquals(e.getTipo(), Mensagem.MNG005.getTipo());
		}
	}
	
	@Test
	public void verificaMensagemUsuarioNaoInformado(){
		
		try {
			validador.confirmaSenha(USUARIO_NAO_INFORMADO, SENHA_INFORMADA);
		} catch (NegocioException e) {
			Assert.assertEquals(e.getMensagemEnum(), Mensagem.MNG004);
			Assert.assertEquals(e.getTipo(), Mensagem.MNG004.getTipo());
		}
	}
	
	@Test
	public void verificaMensagemSenhaUsuarioNaoInformado(){
		
		try {
			validador.confirmaSenha(initUsuario(TipoInitUsuario.USUARIO_NAO_INFORMANDO_SENHA), SENHA_INFORMADA);
		} catch (NegocioException e) {
			Assert.assertEquals(e.getMensagemEnum(), Mensagem.MNG004);
			Assert.assertEquals(e.getTipo(), Mensagem.MNG004.getTipo());
		}
	}
	
	@Test
	public void verificaMensagemConfirmacaoSenhaNaoInformado(){
		
		try {
			validador.confirmaSenha(initUsuario(TipoInitUsuario.USUARIO_INFORMANDO_SENHA), CONFIRMACAO_SENHA_NAO_INFORMADO);
		} catch (NegocioException e) {
			Assert.assertEquals(e.getMensagemEnum(), Mensagem.MNG004);
			Assert.assertEquals(e.getTipo(), Mensagem.MNG004.getTipo());
		}
	}
}
