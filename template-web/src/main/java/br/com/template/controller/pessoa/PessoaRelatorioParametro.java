package br.com.template.controller.pessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.template.entidades.Pessoa;
import br.com.template.excecao.NegocioException;
import br.com.template.relatorio.parametro.ParametroTemplateRelatorioEnum;
import br.com.template.util.relatorio.AbstractRelatorioParametro;

public class PessoaRelatorioParametro extends AbstractRelatorioParametro{

	public PessoaRelatorioParametro(List<Pessoa> listDetailRelatorio) {
		super(converteLista(listDetailRelatorio));
	}

	private static List<?> converteLista(List<Pessoa> list) {
		
		List<PessoaRelatorioBean> listRelatorio = new ArrayList<>();
		
		for (Pessoa entidade: list){
			
			PessoaRelatorioBean bean = new PessoaRelatorioBean(entidade.getNome(), entidade.getSexo().getValue(), entidade.getDataNascimento());
			
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
}
