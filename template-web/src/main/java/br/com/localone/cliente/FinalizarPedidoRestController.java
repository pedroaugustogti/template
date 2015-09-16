package br.com.localone.cliente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.localone.service.ComandaService;
import br.com.localone.service.MesaService;
import br.com.template.domain.SituacaoPedido;
import br.com.template.dto.CardapioDTO;
import br.com.template.dto.CarrinhoDTO;
import br.com.template.dto.ComandaDTO;
import br.com.template.dto.FiltroComandaDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;
import br.com.template.framework.AbstractRestController;
import br.com.template.framework.GenericSpringServiceController;

@Controller
@PermitAll
public class FinalizarPedidoRestController extends AbstractRestController {
	
	@Autowired
	private MesaService mesaService;
	
	@Autowired
	private GenericSpringServiceController<Cardapio, Long> serviceGenericCardapio;
	
	@Autowired
	private GenericSpringServiceController<Mesa, Long> serviceGenericMesa;
	
	@Autowired
	private ComandaService comandaService;
	
	@RequestMapping(value="/finalizar-pedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> finalizarPedido(@RequestBody CarrinhoDTO carrinhoDTO) {
		
		if (carrinhoDTO == null || StringUtils.isBlank(carrinhoDTO.getCodigo())){
			
			return status(HttpStatus.BAD_REQUEST);
		}
		
		Mesa mesa = mesaService.mesaPorCodigo(carrinhoDTO.getCodigo());
		
		if (mesa == null){
			return status(HttpStatus.BAD_REQUEST);
		}
		
		vinculaPedidosCarrinhoNaMesa(mesa, carrinhoDTO.getListPedidosCarrinho());
		Comanda comanda = getComandaPorMesa(mesa);
		
		if (comanda == null){
			return status(HttpStatus.BAD_REQUEST);
		}
		
		ComandaDTO comandaDTO = new ComandaDTO();
		comandaDTO.setIdComanda(comanda.getId());
		
		return toJson(comandaDTO);
	}

	private Comanda getComandaPorMesa(Mesa mesa) {
		
		FiltroComandaDTO filtroComandaDTO = new FiltroComandaDTO();
		
		filtroComandaDTO.setMesaId(mesa.getId());
		Comanda comanda = comandaService.getComandaMesa(filtroComandaDTO);
		return comanda;
	}

	private void vinculaPedidosCarrinhoNaMesa(Mesa mesa, List<CardapioDTO> cardapioDTOs) {
		
		if (cardapioDTOs != null){
			
			List<Pedido> listPedido = new ArrayList<Pedido>();
			
			Comanda comanda = mesa.getComanda();
			
			if (comanda == null){
				
				comanda = new Comanda();
				comanda.setMesa(mesa);
			}
			
			
			for (CardapioDTO cardapioDTO : cardapioDTOs){
				
				Cardapio cardapio = serviceGenericCardapio.getById(Cardapio.class, cardapioDTO.getId());
				
				for (int i = 0; i < cardapioDTO.getQuantidade(); i++){
					
					Pedido pedido = new Pedido();
					
					pedido.setCardapio(cardapio);
					pedido.setComanda(comanda);
					pedido.setSituacao(SituacaoPedido.FILA);
					pedido.setHorarioSolicitacao(Calendar.getInstance());
					
					listPedido.add(pedido);
				}
			}
			
			comanda.setListPedido(listPedido);
			mesa.setComanda(comanda);
			
			serviceGenericMesa.salvar(mesa);
		}
	}
}