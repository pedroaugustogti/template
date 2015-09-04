package br.com.localone.dao;

import java.util.Date;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.template.entidades.DespesaEntrada;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaEntradaDAO extends ConsultasDaoJpa<DespesaEntrada>{
	
	public Number pesquisaTotalDespesasPeloPeriodo(Date dataInicio, Date dataFinal) {

		Criteria criteria = super.novoCriterio();
		
		criteria.add(Restrictions.between("data", dataInicio, dataFinal));
		
		criteria.setProjection(Projections.sum("preco"));
		
		return (Number) criteria.uniqueResult();
	}
}