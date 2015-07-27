package br.com.template.autorizacao;

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

	CADASTRAR_PESSOA,
	ALTERAR_PESSOA,
	EXCLUIR_PESSOA,
	GERAR_RELATORIO_CONSULTA,
	PESQUISAR_PESSOA, 
	VISUALIZAR_PESSOA,
	
	//Produto
	CADASTRAR_PRODUTO,
	EXCLUIR_PRODUTO,
	ALTERAR_PRODUTO,
	
	//Estoque
	CADASTRAR_ESTOQUE,
	EXCLUIR_ESTOQUE,
	ALTERAR_ESTOQUE;
}
