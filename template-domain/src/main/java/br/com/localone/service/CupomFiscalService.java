package br.com.localone.service;

import java.util.List;

import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;

/**
 * 
 * @author pedro.oliveira
 * 
 *
 */
public interface CupomFiscalService {

	void gerarCumpomFiscal(Mesa mesa, List<Pedido> listPedidos);
}
