package br.com.template.util;

import java.io.InputStream;
import java.util.Scanner;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import br.com.template.domain.EmailEnum;
import br.com.template.domain.Mensagem;
import br.com.template.excecao.NegocioException;

/**
 * The Class EmailUtils.
 */
@Stateless
public class EmailUtils {
	
	/** The Constant ENCODING. */
	public static final String ENCODING = "UTF-8";
	
	/** The Constant FINAL_ARQUIVO. */
	private static final String FINAL_ARQUIVO = "\\Z";

	/**
	 * Formata email.
	 *
	 * @param arquivoEmail the arquivo email
	 * @param parametros the parametros
	 * @return the string
	 * @throws NegocioException 
	 */
	public String formataEmail(EmailEnum arquivoEmail, String... parametros) throws NegocioException {

		InputStream stream = getClass().getClassLoader().getResourceAsStream(arquivoEmail.getArquivo());
		Scanner sc = new Scanner(stream, ENCODING);
		String msg =  substituiParametros(sc.useDelimiter(FINAL_ARQUIVO).next(), arquivoEmail.getParametros(), parametros);
		sc.close();
		
		return msg;
	}
	
	/**
	 * Substitui parametros.
	 *
	 * @param email the email
	 * @param parametros the parametros
	 * @param valorParametros the valor parametros
	 * @return the string
	 * @throws NegocioException 
	 */
	private String substituiParametros(String email, String[] parametros, String[] valorParametros) throws NegocioException {
		
		String emailFormatado = email;
		
		verificaValorParametros(valorParametros);
		
		for (int index = 0; index < valorParametros.length; index++){
			emailFormatado = StringUtils.replace(emailFormatado, parametros[index], valorParametros[index]);
		}
		
		return emailFormatado;
	}

	private void verificaValorParametros(String[] valorParametros) throws NegocioException {
		
		if (valorParametros == null){
			throw new NegocioException(Mensagem.MEI001);
		}
		
	}
}