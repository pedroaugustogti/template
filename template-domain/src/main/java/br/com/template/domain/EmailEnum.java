package br.com.template.domain;

public enum EmailEnum {
	
	EMAIL_TEMPLATE("emails/template/template.html","Assunto do Email","{url}");
			
	// CHECKSTYLE:ON
	/** The arquivo. */
	private String arquivo;
	
	/** The assunto. */
	private String assunto;
	
	/** The parametros. */
	private String[] parametros;
	
	/**
	 * Instantiates a new email enum.
	 *
	 * @param arquivo the arquivo
	 * @param assunto the assunto
	 * @param parametro the parametro
	 */
	EmailEnum(String arquivo, String assunto, String... parametro){
		this.arquivo = arquivo;
		this.parametros = parametro;
		this.assunto = assunto;
	}

	/**
	 * Gets the parametros.
	 *
	 * @return the parametros
	 */
	public String[] getParametros() {
		return parametros;
	}

	/**
	 * Gets the arquivo.
	 *
	 * @return the arquivo
	 */
	public String getArquivo() {
		return arquivo;
	}

	/**
	 * Gets the assunto.
	 *
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}
}