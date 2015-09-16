package br.com.localone.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaDAO extends ConsultasDaoJpa<Despesa>{
	
	@SuppressWarnings("unchecked")
	public Double pesquisaTotalDespesasPeloPeriodo(Date dataInicio, Date dataFinal) {

		Criteria criteria = super.novoCriterio();
		
		criteria.add(Restrictions.between("horarioSolicitacao", dataInicio, dataFinal));
		
		Double totalDespesasNoPeriodo = calculaTodasDespesas(criteria.list());
		
		return totalDespesasNoPeriodo;
	}

	private Double calculaTodasDespesas(List<Despesa> list) {
		
		double valorTotal = 0.0;
		
		for (Despesa despesa : list){
			
			for (DespesaSocio despesaSocio: despesa.getListDespesaSocio()){
				
				valorTotal += despesaSocio.getValor();
			}
		}
		
		return valorTotal;
	}
}