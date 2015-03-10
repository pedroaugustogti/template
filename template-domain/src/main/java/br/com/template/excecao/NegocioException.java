package br.com.template.excecao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.template.mensagem.MensagemNegocio;
import br.com.template.mensagem.TipoMensagem;

public class NegocioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6019618454815377751L;
	private static final String ARQUIVO_CONFIGURACAO_MENSAGENS = "/msg-negocio.properties";
	
	private static Properties properties;
	private String valorChave;
	private TipoMensagem tipo;
	
	static {
		InputStream inStream;
		properties = new Properties();
		inStream = NegocioException.class.getResourceAsStream(ARQUIVO_CONFIGURACAO_MENSAGENS);
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NegocioException(MensagemNegocio msg) {
		super(msg.name());
		valorChave = properties.getProperty(msg.name());
		tipo = msg.getTipo();
	}
	
	public String getMensagem() {
		return valorChave;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}
}