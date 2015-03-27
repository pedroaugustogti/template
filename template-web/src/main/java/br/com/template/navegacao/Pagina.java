package br.com.template.navegacao;

public enum Pagina {

	LOGIN("/login.jsf"),
	CADASTRAR_USUARIO("/cadastrarUsuario.jsf"),
	CADASTRAR_PESSOA("/pages/cadastrarPessoa.jsf"),
	CONSULTAR_PESSOA("/pages/consultarPessoa.jsf");
	
	private String valor;
	
	private Pagina(String valor){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}