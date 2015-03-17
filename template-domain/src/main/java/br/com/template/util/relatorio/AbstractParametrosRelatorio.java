package br.com.template.util.relatorio;

import java.util.List;
import java.util.Map;

import br.com.template.excecao.NegocioException;

public abstract class AbstractParametrosRelatorio {

	protected abstract Map<String,Object> getParametrosRelatorio() throws NegocioException;
	
	protected abstract List<?> getDataSourceList() throws NegocioException;
}
