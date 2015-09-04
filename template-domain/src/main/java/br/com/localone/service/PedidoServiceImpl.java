package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.dao.PedidoDAO;
import br.com.template.domain.SituacaoPedido;
import br.com.template.dto.FiltroPedidoDTO;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Pedido;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class PedidoServiceImpl implements PedidoService{
	
	@EJB
	private ConsultasDaoJpa<Pedido> reposiroty;
	
	@EJB
	private PedidoDAO pedidoDAO;

	@Override
	public List<Pedido> pesquisar(FiltroPedidoDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Pedido.class);
	}

	@Override
	public List<Pedido> pedidosDoDia(SituacaoPedido situacao) {
		return pedidoDAO.pedidosDoDia(situacao);
	}

	@Override
	public List<Pedido> pedidosNaoCancelados(Comanda comanda) {
		return pedidoDAO.pedidosNaoCancelados(comanda);
	}
}