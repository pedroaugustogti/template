package br.com.localone.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import bemajava.Bematech;
import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;

@Stateless
public class CupomFiscalServiceImpl implements CupomFiscalService{

	@Override
	public void gerarCumpomFiscal(Mesa mesa, List<Pedido> listPedidos) {
		
		String formaPagamento = null;
		String numeroMesa = mesa.getNumMesa().toString();
		String valorConta = valorConta(listPedidos);
		
		Bematech.AbreCupom(mesa.getCpfCliente());
		
		for (Pedido pedido : pedidosAcumulados(listPedidos)){
			
			String descricao = pedido.getCardapio().getDescricao();
			String valorUnitario = pedido.getCardapio().getPrecoFormat();
			String codigoItem = pedido.getCardapio().getId().toString();
			String qntItem = String.valueOf(pedido.getQuantidade());
			
			Bematech.RegistraVenda(numeroMesa, codigoItem, descricao, "0", qntItem, valorUnitario, "0", "0");
		}
		
		formaPagamento = mesa.getFormaPagamento().getLabel();
		
		Bematech.IniciaFechamentoCupom("0", "0", "0");
		
		Bematech.EfetuaFormaPagamento(formaPagamento, valorConta);
		
		Bematech.FechaCupom(formaPagamento, "0", "0", "", valorConta, "Noix!");
	}

	private String valorConta(List<Pedido> listPedidos) {
		
		double valorConta = 0.0;
		
		for (Pedido pedido : listPedidos){
			
			valorConta += (pedido.getCardapio().getPreco() * pedido.getQuantidade());
		}
		
		return String.valueOf(valorConta);
	}

	private List<Pedido> pedidosAcumulados(List<Pedido> listPedidos) {
		
		List<Pedido> listPedidosAcumulados = new ArrayList<Pedido>();
		
		for (Pedido pedido : listPedidos){
			
			if (listPedidosAcumulados.isEmpty()){
				
				listPedidosAcumulados.add(pedido);
				continue;
			}
			
			for (Pedido pedidoAcumulado : listPedidosAcumulados){
				
				if (!pedido.getCardapio().getId().equals(pedidoAcumulado.getCardapio().getId())){
					
					listPedidosAcumulados.add(pedido);
					
				}else{
					
					pedido.setQuantidade(pedido.getQuantidade() + 1);
				}
			}
		}
		
		return listPedidosAcumulados;
	}
}