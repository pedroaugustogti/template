package br.com.localone.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.template.entidades.Balanco;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class BalancoDAO extends ConsultasDaoJpa<Balanco>{
	
	@SuppressWarnings("unchecked")
	public List<Balanco> pesquisar(Date dataInicio, Date dataFim) {
		Criteria criteria = super.novoCriterio();
		
		criteria.add(Restrictions.between("fechamentoConta", dataInicio, dataFim));
		
		criteria.addOrder(Order.asc("fechamentoConta"));
		
		return criteria.list();
	}
	
	public static void main(String[] args) {
		Persistence.generateSchema("desenvolvimento", null);
	}
}