package br.com.localone.admin.despesa.entrada;

import br.com.template.dto.FiltroDespesaEntradaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.framework.AbstractManageBean;

public abstract class DespesaEntradaSuperController extends AbstractManageBean{
	
	protected FiltroDespesaEntradaDTO filtroDespesaEntradaDTO;
	protected DespesaEntrada despesaEntrada;
	
	public void inicio(){
		
		despesaEntrada = new DespesaEntrada();
		Despesa despesa = new Despesa();
		despesaEntrada.setDespesa(despesa);
		filtroDespesaEntradaDTO = new FiltroDespesaEntradaDTO();
	}
	
	public void selecionaDespesa(Despesa despesa){
		
		despesaEntrada.setDespesa(despesa);
	}
	
	public FiltroDespesaEntradaDTO getFiltroDespesaEntradaDTO() {
		return filtroDespesaEntradaDTO;
	}

	public DespesaEntrada getDespesaEntrada() {
		return despesaEntrada;
	}
}