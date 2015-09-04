package br.com.localone.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.template.domain.SituacaoPedido;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Pedido;
import br.com.template.generics.ConsultasDaoJpa;
import br.com.template.util.DataUtil;

@Stateless
public class PedidoDAO extends ConsultasDaoJpa<Pedido>{
	
	@SuppressWarnings("unchecked")
	public List<Pedido> pedidosDoDia(SituacaoPedido situacao) {
		
		Criteria criteria = super.novoCriterio();
		
		criteria.add(
				Restrictions.between("horarioSolicitacao", DataUtil.getHoraInicialDia(), DataUtil.getHoraFinalDia()));
		
		criteria.add(Restrictions.eqOrIsNull("situacao", situacao));

		criteria.addOrder(Order.asc("horarioSolicitacao"));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> pedidosNaoCancelados(Comanda comanda) {
		
		Criteria criteria = super.novoCriterio();
		
		criteria.add(Restrictions.ne("situacao", SituacaoPedido.CANCELADO));
		
		criteria.createAlias("comanda", "comanda");

		criteria.add(Restrictions.eq("comanda.id", comanda.getId()));
		
		criteria.addOrder(Order.asc("horarioSolicitacao"));
		
		return criteria.list();
	}
}