package br.com.localone.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.template.dto.PedidoDTO;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Pedido;
import br.com.template.framework.AbstractRestController;
import br.com.template.framework.GenericSpringServiceController;

@Controller
@PermitAll
public class ComandaRestController extends AbstractRestController {
	
	@Autowired
	private GenericSpringServiceController<Comanda, Long> comandaGenericService;
	
	@RequestMapping(value="/comanda", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> finalizarPedido(@QueryParam("idComanda") Long idComanda) {
		
		Comanda comanda = comandaGenericService.getById(Comanda.class, idComanda, "listPedido");
		
		List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>(); 
		
		if (comanda != null){
			pedidoDTOs = converteList(comanda.getListPedido());
		}
		
		return toJson(pedidoDTOs);
	}

	private List<PedidoDTO> converteList(List<Pedido> listPedido) {
		
		List<PedidoDTO> listPedidoDTO = new ArrayList<PedidoDTO>();
		
		for (Pedido pedido : listPedido){
			
			PedidoDTO pedidoDTO = new PedidoDTO();
		
			pedidoDTO.setStatus(pedido.getSituacao().getLabel());
			pedidoDTO.setTitulo(pedido.getCardapio().getDescricao());
			
			listPedidoDTO.add(pedidoDTO);
		}
		
		return listPedidoDTO;
	}
}