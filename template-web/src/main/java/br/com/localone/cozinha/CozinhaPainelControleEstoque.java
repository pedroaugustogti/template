package br.com.localone.cozinha;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.template.domain.EmailEnum;
import br.com.template.domain.EmailRemetenteEnum;
import br.com.template.domain.Medida;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.entidades.Estoque;
import br.com.template.entidades.Pedido;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.EmailDTO;
import br.com.template.framework.GenericServiceController;
import br.com.template.framework.InterceptionViewMenssage;
import br.com.template.util.EmailParametro;
import br.com.template.util.EmailProperties;
import br.com.template.util.EmailUtils;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class CozinhaPainelControleEstoque {
	
	@EJB
	private GenericServiceController<Estoque, Long> serviceEstoque;
	
	@EJB
	private GenericServiceController<Cardapio, Long> serviceCardapio;
	
	@Inject
	private Event<EmailDTO> eventEmail;
	
	public void reduzEstoque(final Pedido pedido) {
		
		try {
			atualizaEstoque(pedido);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	private void atualizaEstoque(Pedido pedido) throws NegocioException {
		
		Cardapio cardapio = serviceCardapio.getById(Cardapio.class, pedido.getCardapio().getId(), "listIngredientes");
		
		List<CardapioIngrediente> ingredientes = cardapio.getListIngredientes();
		
		for (CardapioIngrediente ingrediente : ingredientes){
			
			Long idEstoque = ingrediente.getEstoque().getId();
			Estoque estoque = serviceEstoque.getById(Estoque.class, idEstoque);
			
			int qntEstoqueAtualizada = qntEstoqueAtualizado(ingrediente, estoque);;
			
			estoque.setQuantidade(qntEstoqueAtualizada);
			serviceEstoque.salvarSemMensagem(estoque);
		}
	}

	private int qntEstoqueAtualizado(CardapioIngrediente ingrediente,Estoque estoque) throws NegocioException {
		
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

	private void enviaNotificacaoParaReporEstoque(Estoque estoque) throws NegocioException {
		
		EmailDTO emailDTO = new EmailDTO();
		String corpoEmail = montaCorpoEmail(estoque);
		
		emailDTO.setPara(EmailProperties.getValue(EmailRemetenteEnum.REMETENTE_EMAIL_ESTOQUE)); 
		emailDTO.setAssunto(montaAssunto(estoque));
		emailDTO.setMensagem(corpoEmail);
		
		eventEmail.fire(emailDTO);
	}

	private String montaAssunto(Estoque estoque) throws NegocioException {
		
		EmailParametro emailParametro = new EmailParametro();
		
		emailParametro.addParametro("{produto}", estoque.getProduto().getDescricao());
		
		return EmailEnum.EMAIL_ESTOQUE_ACABANDO.assunto(emailParametro);
	}

	private String montaCorpoEmail(Estoque estoque) throws NegocioException {
		
		EmailParametro emailParametro = new EmailParametro();
		
		emailParametro.addParametro("{produto}", estoque.getProduto().getDescricao());
		emailParametro.addParametro("{quantidade}", estoque.getQuantidade().toString());
		emailParametro.addParametro("{medida}", estoque.getMedida().getLabel());
		return EmailUtils.formataEmail(EmailEnum.EMAIL_ESTOQUE_ACABANDO, emailParametro);
	}
}