package br.com.localone.autorizacao;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author pedro.oliveira
 * 
 * <p>Enum reposável por associar AutorizacaoEnum.java (Que já possui as Role.java de autorização para acesso a cada Pagina.java) a cada funcionalidade.</p>
 * 
 *
 */
public enum FuncionalidadeEnum {

	/**
	 * ADMINISTRADOR - Acesso a todas as funcionalidades
	 */
	ADMINISTRADOR(AutorizacaoEnum.ADMINISTRADOR, TipoFuncionalidadeEnum.TODAS),
	
	COZINHEIRO(AutorizacaoEnum.COZINHEIRO, TipoFuncionalidadeEnum.TODAS), 
	
	GARCOM(AutorizacaoEnum.GARCOM, TipoFuncionalidadeEnum.TODAS), 
	
	GERENTE(AutorizacaoEnum.GERENTE, TipoFuncionalidadeEnum.TODAS);
	

	private AutorizacaoEnum autorizacao;
	
	private TipoFuncionalidadeEnum[] funcionalidades;
	
	private FuncionalidadeEnum(AutorizacaoEnum autorizacao, TipoFuncionalidadeEnum... funcionalidades){
		
		this.autorizacao = autorizacao;
		this.funcionalidades = funcionalidades;
	}
	
	/**
	 * Verifica se o tipoFuncionalidade(TipoFuncionalidadeEnum) solicitada está dentro das que tem o {@code auth}(AutorizacaoEnum) do usuario logado. 
	 * 
	 * 
	 * @param auth variavel de instância dentro do AbstractManageBean, inicializada pelo usuario logado
	 * @param tipoFuncionalidade funcionalidade que solicita acesso 
	 * @return true caso o {@code tipoFuncionalidade} esteja dentre as permitidas no {@code auth}
	 */
	public static boolean verificaAutorizacaoComAcessoNaFuncionalidade(AutorizacaoEnum auth, TipoFuncionalidadeEnum tipoFuncionalidade) {
		
		boolean funcionalidadeLiberada = Boolean.FALSE;
		
		for (FuncionalidadeEnum funcionalidade : values()){
			
			if (funcionalidade.autorizacao.equals(auth) && contemFuncionalidade(tipoFuncionalidade, funcionalidade)){
				funcionalidadeLiberada = Boolean.TRUE;
			}
		}
		
		return funcionalidadeLiberada;
	}

	private static boolean contemFuncionalidade(TipoFuncionalidadeEnum tipoFuncionalidade, FuncionalidadeEnum funcionalidade) {
		
		List<TipoFuncionalidadeEnum> tipoFuncionalidadeList = Arrays.asList(funcionalidade.funcionalidades);
		boolean contemFuncionalidade = Boolean.FALSE;
		
		if (tipoFuncionalidadeList.contains(tipoFuncionalidade) || tipoFuncionalidadeList.contains(TipoFuncionalidadeEnum.TODAS)){
			contemFuncionalidade = Boolean.TRUE;
		}
		
		return contemFuncionalidade;
	}
}