package br.com.template.autorizacao;

/**
 * 
 * @author pedro.oliveira
 * 
 * Enum responsável por centralizar todos os caminhos das páginas jsf para serem utilizados como objetos java.
 *
 */
public enum Pagina {
	
	TODAS,
	LOGIN("/login.jsf"),
	CADASTRAR_USUARIO("/cadastrarUsuario.jsf"),
	CADASTRAR_PESSOA("/pages/pessoa/cadastrarPessoa.jsf"),
	ALTERAR_PESSOA("/pages/pessoa/alterarPessoa.jsf"),
	CONSULTAR_PESSOA("/pages/pessoa/consultarPessoa.jsf"), 
	
	//Administração
	PAINEL_ADMIN("/pages/admin/painelAdmin.jsf"),
	
	//Produto
	PAINEL_PRODUTO("/pages/admin/estoque/produto/painelProduto.jsf"),
	CADASTRAR_PRODUTO("/pages/admin/estoque/produto/cadastrarProduto.jsf"),
	ALTERAR_PRODUTO("/pages/admin/estoque/produto/alterarProduto.jsf"), 
	
	//Estoque
	PAINEL_ESTOQUE("/pages/admin/estoque/painelEstoque.jsf"), 
	ALTERAR_ESTOQUE("/pages/admin/estoque/alterarEstoque.jsf"), 
	CADASTRAR_ESTOQUE("/pages/admin/estoque/cadastrarEstoque.jsf");
	
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