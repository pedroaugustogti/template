package br.com.template.domain;


/**
 * 
 * @author pedro.oliveira
 *
 *	<p> Enum que diversifica os tipos de Mensagens do sistema em categorias. </p>
 *	<p> Utilizado no enum Messagem.java </p>
 *	<p> Utilizado nos interceptors de mensagem e na sua super classe AbstractInterceptionMessage.java para enviar mensagem para tela do usuário pelo tipo.</p>	
 *	<p> Exemplo dos tipos: </p>
 *	
 *<ul>
 *
 *	<li>ALERTA - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_WARN</li>
 *	<li>ERRO - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_ERROR</li>
 *	<li>INFO - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_INFO</li>
 *	<li>INTERNO - Não são mostradas ao usuário, utilizada para sobrescrever exeções padrão como Exception.java para NegocioException.java adicionando mensagens específicas, que possa auxiliar melhor ao erro.</li>
 *	<li>DEFAULT - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_INFO já com uma mensagem pré estabelecida</li>
 *	<li>ESTATICO - Textos estáticos</li>
 *</ul>	
 *
 **/
public enum TipoMensagem {

	/**
	 * ALERTA - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_WARN
	 */
	ALERTA,
	/**
	 * ERRO - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_ERROR
	 */
	ERRO,
	/**
	 * INFO - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_INFO
	 */
	INFO,
	/**
	 * INTERNO - Não são mostradas ao usuário, utilizada para sobrescrever exeções padrão como Exception.java para NegocioException.java adicionando mensagens específicas, que possa auxiliar melhor ao erro.
	 */
	INTERNO, 
	/**
	 * DEFAULT - Utiliza para mostrar ao usuário mensagem (JSF) do tipo FacesMessage.SEVERITY_INFO já com uma mensagem pré estabelecida
	 */
	DEFAULT,
	/**
	 * ESTATICO - Textos estáticos
	 */
	ESTATICO;
}
