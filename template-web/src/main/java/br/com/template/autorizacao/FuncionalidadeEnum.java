package br.com.template.autorizacao;

import java.util.Arrays;
import java.util.List;

public enum FuncionalidadeEnum {

	ADMINISTRADOR(AutorizacaoEnum.ADMINISTRADOR, TipoFuncionalidadeEnum.TODAS),
	
	USUARIO(AutorizacaoEnum.USUARIO, TipoFuncionalidadeEnum.PESQUISAR_PESSOA, 
									 TipoFuncionalidadeEnum.GERAR_RELATORIO_CONSULTA,
									 TipoFuncionalidadeEnum.VISUALIZAR_PESSOA);

	private AutorizacaoEnum autorizacao;
	
	private TipoFuncionalidadeEnum[] funcionalidades;
	
	private FuncionalidadeEnum(AutorizacaoEnum autorizacao, TipoFuncionalidadeEnum... funcionalidades){
		
		this.autorizacao = autorizacao;
		this.funcionalidades = funcionalidades;
	}
	
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

	public AutorizacaoEnum getAutorizacao() {
		return autorizacao;
	}

	public TipoFuncionalidadeEnum[] getFuncionalidades() {
		return funcionalidades;
	}
}