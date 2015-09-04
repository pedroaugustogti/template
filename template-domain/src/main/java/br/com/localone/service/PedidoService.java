package br.com.localone.service;

import java.util.List;

import br.com.template.domain.SituacaoPedido;
import br.com.template.dto.FiltroPedidoDTO;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Pedido;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface PedidoService {

	/**
	 * 
	 * @param filtro
	 * @return
	 */
	List<Pedido> pesquisar(FiltroPedidoDTO filtro);

	List<Pedido> pedidosDoDia(SituacaoPedido situacao);

	List<Pedido> pedidosNaoCancelados(Comanda comanda);
	
}
