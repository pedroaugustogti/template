package br.com.template.util.container;

import javax.servlet.http.HttpSession;

import br.com.template.framework.AbstractManageBean;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Todas as chaves de atributos de {@link HttpSession} devem estar descritos neste enum.</p>
 * 
 * <p>Para adicionar atributos de sessão e obter o valor dos mesmos.</p>
 * 
 * <p>Sugestão é utilizar os métodos já existentes na classe {@link AbstractManageBean}</p>
 * 
 * <ul>
 * 	<li>void setAtributoSessao({@link AtributoSessao}, {@link Object});</li>
 * 	<li>{@link Object} getAtributoSessao({@link AtributoSessao})</li>
 * </ul>
 *
 */
public enum AtributoSessao {

	PERMISSOES_USUARIO,
	USUARIO,
	MENSAGEM, 
	OBJ_ALTERAR_PESSOA, 
	OBJ_ALTERAR_PRODUTO, 
	OBJ_ALTERAR_ESTOQUE, 
	OBJ_ALTERAR_CARDAPIO, 
	OBJ_MESA, 
	OBJ_ALTERAR_FUNCIONARIO, 
	OBJ_ALTERAR_USUARIO, 
	OBJ_BALANCO_PAINEL_MENSAL, 
	OBJ_BALANCO_PAINEL_DIARIO, 
	OBJ_BALANCO_PAINEL_HORA, 
	OBJ_ALTERAR_DESPESA, 
	OBJ_ALTERAR_DESPESA_ENTRADA;
	
}