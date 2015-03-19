package br.com.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.template.entidades.EntidadeExemplo;
import br.com.template.excecao.NegocioException;
import br.com.template.relatorio.parametro.ParametroTemplateRelatorioEnum;
import br.com.template.util.relatorio.AbstractParametrosRelatorio;

public class TemplateBeanParametroRelatorio extends AbstractParametrosRelatorio{

	private List<?> listDetailRelatorio;

	public TemplateBeanParametroRelatorio(List<EntidadeExemplo> listDetailRelatorio) {
		
		this.listDetailRelatorio = converteLista(listDetailRelatorio);
	}

	private List<?> converteLista(List<EntidadeExemplo> list) {
		
		List<BeanRelatorio> listRelatorio = new ArrayList<>();
		
		for (EntidadeExemplo entidade: list){
			
			BeanRelatorio bean = new BeanRelatorio(entidade.getNome(), entidade.getSexo().getValue(), entidade.getDataNascimento());
			
			listRelatorio.add(bean);
		}
		
		return listRelatorio;
	}

	@Override
	protected Map<String, Object> getParametrosRelatorio() throws NegocioException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put(ParametroTemplateRelatorioEnum.TITULO.chave(), ParametroTemplateRelatorioEnum.TITULO.getValor());
		
		return params;
	}

	@Override
	protected List<?> getDataSourceList() {
		return listDetailRelatorio;
	}
}
