package br.com.localone.admin.cardapio;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.template.domain.Medida;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class IngredientePainelValidacao {

	public void adicionarIngrediente(CardapioIngrediente cardapioIngrediente, List<CardapioIngrediente> listaIngredientes) throws NegocioException{
		
		campoObrigatorio(cardapioIngrediente);
		
		quantidadeDoIngredienteSuperiorAoQueTemEmEstoque(cardapioIngrediente);
		
		identificaIgreditenteJaAdicionadoNaLista(cardapioIngrediente,listaIngredientes);
	}

	private void quantidadeDoIngredienteSuperiorAoQueTemEmEstoque(CardapioIngrediente ingrediente) throws NegocioException {
		
		Estoque estoque = ingrediente.getEstoque();
		
		if (ingrediente.getMedida().equals(estoque.getMedida())){
			
			if (ingrediente.getQuantidade() > ingrediente.getEstoque().getQuantidade()){
				throw new NegocioException(Mensagem.MNG020);
			}
		
		}else if (Medida.isSubMedida(estoque.getMedida(), ingrediente.getMedida())){
			
			//1000 significa a quantidade descontada da subMedida no caso 1 KG = 1000 G
			if ((ingrediente.getQuantidade() / 1000.00) > ingrediente.getEstoque().getQuantidade()){
				throw new NegocioException(Mensagem.MNG020);
			}
		}
	}

	private void campoObrigatorio(CardapioIngrediente cardapioIngrediente) throws NegocioException {
		
		if (cardapioIngrediente.getEstoque().getId() == null){
			throw new NegocioException(Mensagem.MNG018);
		}
		
		if (cardapioIngrediente.getQuantidade() == null || cardapioIngrediente.getQuantidade().intValue() == BigInteger.ZERO.intValue()){
			throw new NegocioException(Mensagem.MNG019);
		}
	}

	private void identificaIgreditenteJaAdicionadoNaLista(CardapioIngrediente cardapioIngrediente, List<CardapioIngrediente> listaIngredientes)throws NegocioException {
		
		if (listaIngredientes != null && !listaIngredientes.isEmpty()){
			
			for (CardapioIngrediente igrediente : listaIngredientes){
				
				if (igrediente.getEstoque().getId().equals(cardapioIngrediente.getEstoque().getId())){
					
					throw new NegocioException(Mensagem.MNG017);
				}
			}
		}
	}
}