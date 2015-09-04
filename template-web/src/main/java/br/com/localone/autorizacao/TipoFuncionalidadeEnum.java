package br.com.localone.autorizacao;

/**
 * 
 * @author pedro.oliveira
 *	
 *	<p>Utilizado para saber qual funcionalidade está sendo executada para realizar a autorização</p>
 *
 *	<p>Esse enum deve ser utilizado na página Xhtml/jsf para acessar o método na classe AbstractManageBean.java o método autorizaFuncionalidade(TipoFuncionalidadeEnum)</p>
 *	
 *	<p>Exemplo de implementação no arquivo xhtml:</p>
 *	
 *	<ul>
 *		<li>rendered="#{manageBean.autorizaFuncionalidade('CADASTRAR_PESSOA')}"</li>
 *	</ul>
 *
 *	<p><b>NOTA: O managebean em execução deve extender a classe AbstractManageBean</b></p>
 */
public enum TipoFuncionalidadeEnum {
	
	TODAS,

	//Produto
	CADASTRAR_PRODUTO,
	EXCLUIR_PRODUTO,
	ALTERAR_PRODUTO,
	
	//Estoque
	CADASTRAR_ESTOQUE,
	EXCLUIR_ESTOQUE,
	ALTERAR_ESTOQUE,
	
	//Estoque
	CADASTRAR_CARDAPIO,
	EXCLUIR_CARDAPIO,
	ALTERAR_CARDAPIO,
	
	//Funcionario
	CADASTRAR_FUNCIONARIO,
	EXCLUIR_FUNCIONARIO,
	ALTERAR_FUNCIONARIO,
	
	//Usario
	CADASTRAR_USUARIO,
	EXCLUIR_USUARIO,
	ALTERAR_USUARIO,
	
	//Mesa pedido
	CANCELAR_PEDIDO,
	
	//Despesa
	CADASTRAR_DESPESA,
	EXCLUIR_DESPESA,
	ALTERAR_DESPESA,
	
	//Despesa de entrada
	CADASTRAR_DESPESA_ENTRADA,
	EXCLUIR_DESPESA_ENTRADA,
	ALTERAR_DESPESA_ENTRADA;
	
}
