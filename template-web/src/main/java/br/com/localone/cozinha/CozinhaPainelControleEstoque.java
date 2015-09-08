package br.com.localone.cozinha;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.template.domain.Medida;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.entidades.Estoque;
import br.com.template.entidades.Pedido;
import br.com.template.framework.GenericServiceController;
import br.com.template.framework.EmailDTO;

@Stateless
public class CozinhaPainelControleEstoque {
	
	@EJB
	private GenericServiceController<Estoque, Long> serviceEstoque;
	
	@Inject
	private Event<EmailDTO> eventEmail;
	
	public void reduzEstoque(final Pedido pedido) {
		
		atualizaEstoque(pedido);
	}

	private void atualizaEstoque(Pedido pedido) {
		
		List<CardapioIngrediente> ingredientes = pedido.getCardapio().getListIngredientes();
		
		for (CardapioIngrediente ingrediente : ingredientes){
			
			Long idEstoque = ingrediente.getEstoque().getId();
			Estoque estoque = serviceEstoque.getById(Estoque.class, idEstoque);
			
			int qntEstoqueAtualizada =  qntEstoqueAtualizado(ingrediente, estoque);;
			
			estoque.setQuantidade(qntEstoqueAtualizada);
			serviceEstoque.salvarSemMensagem(estoque);
		}
	}

	private int qntEstoqueAtualizado(CardapioIngrediente ingrediente,Estoque estoque) {
		
		Integer qntEmEstoque = estoque.getQuantidade();
		Integer qntGastoNoPedido = ingrediente.getQuantidade();
		
		int qntEstoqueAtualizada = qntEmEstoque;
		
		if (estoque.getMedida().equals(ingrediente.getMedida())){
			
			qntEstoqueAtualizada = qntEmEstoque - qntGastoNoPedido;
			
		}else if (Medida.isSubMedida(estoque.getMedida(), ingrediente.getMedida())){
			
			qntEstoqueAtualizada = qntEmEstoque - (qntGastoNoPedido / 1000);
		}
		
		if (qntEstoqueAtualizada <= estoque.getQntReposicao()){
			enviaNotificacaoParaReporEstoque(estoque);
		}
		
		return qntEstoqueAtualizada;
	}

	private void enviaNotificacaoParaReporEstoque(Estoque estoque) {
		
		EmailDTO emailDTO = new EmailDTO();
		
		emailDTO.setTo("pedroaugusto.gti@gmail.com"); 
		emailDTO.setSubject("[Local One] - Estoque de "+estoque.getProduto().getDescricao()+" em baixa!");
		emailDTO.setMessage("Faltam apenas "+estoque.getQuantidade()+" "+estoque.getMedida().name()+" para acabar o estoque de "+ estoque.getProduto().getDescricao());
		eventEmail.fire(emailDTO);
	}
}