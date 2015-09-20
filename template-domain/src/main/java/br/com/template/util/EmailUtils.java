package br.com.template.util;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import br.com.template.domain.EmailEnum;
import br.com.template.excecao.NegocioException;

/**
 * The Class EmailUtils.
 */
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
	public static String formataEmail(EmailEnum arquivoEmail, EmailParametro parametros) {

		InputStream stream = EmailUtils.class.getClassLoader().getResourceAsStream(arquivoEmail.getArquivo());
		Scanner sc = new Scanner(stream, ENCODING);
		String msg = null;
		
		try {
			msg = substituiParametros(sc.useDelimiter(FINAL_ARQUIVO).next(), arquivoEmail.getParametros(), parametros);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
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
	private static String substituiParametros(String email, String[] parametros, EmailParametro parametro) throws NegocioException {
		
		String emailFormatado = email;
		
		for (EmailParametro emailParametro : parametro.getListParametros()){
			
			if (emailFormatado.contains(emailParametro.getChaveParametro())){
				
				emailFormatado = StringUtils.replace(emailFormatado, emailParametro.getChaveParametro(), emailParametro.getValorParametro());
			}
		}
		
		return emailFormatado;
	}

	public static String substituiParametroAssunto(String assunto, EmailParametro parametros, String[] chaveParametros) {
		
		String assuntoEmail = null;
		
		try {
			assuntoEmail = substituiParametros(assunto, chaveParametros, parametros);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		return assuntoEmail;
	}
}