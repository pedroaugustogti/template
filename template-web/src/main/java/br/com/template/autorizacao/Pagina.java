package br.com.template.autorizacao;

/**
 * 
 * @author pedro.oliveira
 * 
 * Enum responsável por centralizar todos os caminhos das páginas jsf para serem utilizados como objetos java.
 *
 */
public enum Pagina {

	LOGIN("/login.jsf"),
	CADASTRAR_USUARIO("/cadastrarUsuario.jsf"),
	CADASTRAR_PESSOA("/pages/pessoa/cadastrarPessoa.jsf"),
	ALTERAR_PESSOA("/pages/pessoa/alterarPessoa.jsf"),
	CONSULTAR_PESSOA("/pages/pessoa/consultarPessoa.jsf"), 
	TODAS;
	
	private String valor;
	
	private Pagina(){
		//nada a fazer.
	}
	
	private Pagina(String valor){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}