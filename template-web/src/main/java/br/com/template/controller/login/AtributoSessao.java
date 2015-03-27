package br.com.template.controller.login;

public enum AtributoSessao {

	PERMISSOES_USUARIO("roles");
	
	private String chave;
	
	private AtributoSessao(String chave){
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}
}