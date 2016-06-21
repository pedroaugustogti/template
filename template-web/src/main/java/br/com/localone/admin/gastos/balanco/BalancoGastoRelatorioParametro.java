package br.com.localone.admin.gastos.balanco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.template.domain.Empresa;
import br.com.template.excecao.NegocioException;
import br.com.template.relatorio.parametro.ParametroTemplateRelatorioEnum;
import br.com.template.util.relatorio.AbstractRelatorioParametro;

public class BalancoGastoRelatorioParametro extends AbstractRelatorioParametro{
	
	private Empresa empresa;

	public BalancoGastoRelatorioParametro(List<BalancoGastoBean> listDetailRelatorio, Empresa empresa) {
		super(converteLista(listDetailRelatorio));
		
		this.empresa = empresa;
	}

	private static List<?> converteLista(List<BalancoGastoBean> list) {
		
		List<BalancoGastoRelatorioBean> listRelatorio = new ArrayList<>();
		
		for (BalancoGastoBean bg: list){
			
			String nome = bg.getSocio().getPessoa().getNome();
			String quota = bg.getQuota() + "%";
			String valorPagar = bg.getValorPagarFormat();
			String valorReceber = bg.getValorReceberFormat();
			
			BalancoGastoRelatorioBean bean = new BalancoGastoRelatorioBean(nome, quota, valorReceber, valorPagar);
			
			listRelatorio.add(bean);
		}
		
		return listRelatorio;
	}

	@Override
	protected Map<String, Object> getParametrosRelatorio() throws NegocioException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put(ParametroTemplateRelatorioEnum.TITULO.chave(), empresa.getLabel());
		
		return params;
	}
}
