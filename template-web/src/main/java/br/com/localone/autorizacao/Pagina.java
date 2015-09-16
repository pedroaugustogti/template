package br.com.localone.autorizacao;

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

	//Pessoa
	CADASTRAR_PESSOA("/pages/pessoa/cadastrarPessoa.jsf"),
	ALTERAR_PESSOA("/pages/pessoa/alterarPessoa.jsf"),
	CONSULTAR_PESSOA("/pages/pessoa/consultarPessoa.jsf"), 
	
	/** Administração **/
	//Administração
	PAINEL_ADMIN("/pages/admin/painelAdmin.jsf"),
	
	//Produto
	PAINEL_PRODUTO("/pages/admin/estoque/produto/painelProduto.jsf"),
	CADASTRAR_PRODUTO("/pages/admin/estoque/produto/cadastrarProduto.jsf"),
	ALTERAR_PRODUTO("/pages/admin/estoque/produto/alterarProduto.jsf"), 
	
	//Estoque
	PAINEL_ESTOQUE("/pages/admin/estoque/painelEstoque.jsf"), 
	ALTERAR_ESTOQUE("/pages/admin/estoque/alterarEstoque.jsf"), 
	CADASTRAR_ESTOQUE("/pages/admin/estoque/cadastrarEstoque.jsf"),
	
	//Cardapio
	PAINEL_CARDAPIO("/pages/admin/cardapio/painelCardapio.jsf"),
	CADASTRAR_CARDAPIO("/pages/admin/cardapio/cadastrarCardapio.jsf"),
	ALTERAR_CARDAPIO("/pages/admin/cardapio/alterarCardapio.jsf"), 
	
	PAINEL_MESA_ADMIN("/pages/admin/mesa/painelMesaAdmin.jsf"), 
	
	/** Garçom **/
	//Mesa
	PAINEL_MESA("/pages/mesa/painelMesa.jsf"), 
	
	//cliente
	PAINEL_CLIENTE("/pages/cliente/painelCliente.jsf"), 
	CLIENTE_MENU("/pages/cliente/menuCliente.jsf"), 
	
	//cozinha
	PAINEL_COZINHA("/pages/cozinha/painelCozinha.jsf"), 
	
	//garçom
	PAINEL_ENTREGA_GARCOM("/pages/garcom/painelGarcomEntrega.jsf"), 
	
	//funcionario
	PAINEL_FUNCIONARIO("/pages/admin/funcionario/painelFuncionario.jsf"),
	ALTERAR_FUNCIONARIO("/pages/admin/funcionario/alterarFuncionario.jsf"),
	CADASTRAR_FUNCIONARIO("/pages/admin/funcionario/cadastrarFuncionario.jsf"), 
	
	//usuario
	PAINEL_USUARIO("/pages/admin/usuario/painelUsuario.jsf"), 
	CADASTRAR_USUARIO("/pages/admin/usuario/cadastrarUsuario.jsf"),
	ALTERAR_USUARIO("/pages/admin/usuario/alterarUsuario.jsf"), 
	
	//Balanço Financeiro
	PAINEL_BALANCO("/pages/admin/balanco/painelBalancoFinanceiro.jsf"), 
	BALANCO_DETALHE_ANUAL("/pages/admin/balanco/painelBalancoFinanceiroDetalheAnual.jsf"), 
	BALANCO_DETALHE_MENSAL("/pages/admin/balanco/painelBalancoFinanceiroDetalheMensal.jsf"), 
	BALANCO_DETALHE_DIARIO("/pages/admin/balanco/painelBalancoFinanceiroDetalheDiario.jsf"), 
	
	//Despesa
	PAINEL_DESPESA("/pages/admin/controleGasto/despesa/painelDespesa.jsf"), 
	CADASTRAR_DESPESA("/pages/admin/controleGasto/despesa/cadastrarDespesa.jsf"),
	
	//Receita
	PAINEL_RECEITA("/pages/admin/controleGasto/receita/painelReceita.jsf"),
	CADASTRAR_RECEITA("/pages/admin/controleGasto/receita/cadastrarReceita.jsf");
	
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