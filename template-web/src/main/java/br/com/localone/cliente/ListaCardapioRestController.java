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

import br.com.localone.service.CardapioService;
import br.com.template.domain.CategoriaMenu;
import br.com.template.domain.Situacao;
import br.com.template.dto.CardapioDTO;
import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.framework.AbstractRestController;

@Controller
@PermitAll
public class ListaCardapioRestController extends AbstractRestController {

	private static final String VIRGULA = ", ";
	private static final String E = " e ";
	private static final String PONTO_FINAL = ".";
	
	@Autowired
	private CardapioService cardapioService;
	
	@RequestMapping(value="/prato",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> pratosCategoria(@QueryParam("categoria") String categoria) {
		
		FiltroCardapioDTO filtroCardapioDTO = new FiltroCardapioDTO();
		
		filtroCardapioDTO.setCategoria(CategoriaMenu.categoriaPorName(categoria));
		filtroCardapioDTO.setSituacao(Situacao.ATIVO);
		
		List<Cardapio> listCardapio = cardapioService.pesquisar(filtroCardapioDTO,"listIngredientes");
		
		List<CardapioDTO> listCardapioDTO = conversorCardapio(listCardapio);
		
		return toJson(listCardapioDTO);
	}

	private List<CardapioDTO> conversorCardapio(List<Cardapio> listCardapio) {

		List<CardapioDTO> listCardapioDTO = new ArrayList<CardapioDTO>();
		
		for (Cardapio cardapio : listCardapio){
			
			CardapioDTO cardapioDTO = new CardapioDTO();
			
			cardapioDTO.setId(cardapio.getId());
			cardapioDTO.setDescricao(montaListaIngredientesFormatada(cardapio.getListIngredientes()));
			cardapioDTO.setTitulo(cardapio.getDescricao());
			cardapioDTO.setPreco(cardapio.getPreco());
			
			listCardapioDTO.add(cardapioDTO);
		}
		
		return listCardapioDTO;
	}

	private String montaListaIngredientesFormatada(List<CardapioIngrediente> listIngredientes) {

		StringBuilder descricaoIngredientes = new StringBuilder();
		
		for (int i=0; i <  listIngredientes.size(); i++){
			
			CardapioIngrediente cardapioIngrediente = listIngredientes.get(i);
			
			String nomeProduto = cardapioIngrediente.getEstoque().getProduto().getDescricao();
			
			descricaoIngredientes.append(nomeProduto);
			
			if (i == listIngredientes.size() - 2){
				descricaoIngredientes.append(E);
			}else if (i <= listIngredientes.size() - 3){
				descricaoIngredientes.append(VIRGULA);
			}else if (i == listIngredientes.size() - 1){
				descricaoIngredientes.append(PONTO_FINAL);
			}
		}
		
		return descricaoIngredientes.toString();
	}
}