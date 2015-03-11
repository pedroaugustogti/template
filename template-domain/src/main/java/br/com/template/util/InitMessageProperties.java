package br.com.template.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.template.dominio.MensagemNegocio;

public final class InitMessageProperties {
	
	private static final String ARQUIVO_CONFIGURACAO_MENSAGENS = "/msg-negocio.properties";
	
	private static Properties properties;

	static {
		InputStream inStream;
		properties = new Properties();
		inStream = InitMessageProperties.class.getResourceAsStream(ARQUIVO_CONFIGURACAO_MENSAGENS);
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
		return properties;
	}
	
	public static String getValue(MensagemNegocio mensagemNegocio) {
		return properties.getProperty(mensagemNegocio.name());
	}
}