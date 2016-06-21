package br.com.localone.admin.cardapio;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import br.com.template.domain.Medida;
import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.entidades.Estoque;
import br.com.template.excecao.NegocioException;
import br.com.template.util.DinheiroUtil;

@Stateful
public class IngredientePainel {

	private CardapioIngrediente ingrediente;
	private List<CardapioIngrediente> ingredientes;
	
	private Cardapio cardapio;
	
	private int indexIngrediente; 
	private Integer ingredienteId;
	
	private FiltroEstoqueDTO filtroEstoqueDTO;
	
	@EJB
	private IngredientePainelValidacao validacao;
	private String valorSugerido;
	
	@PostConstruct
	public void inicio(){
		
		indexIngrediente = BigInteger.ZERO.intValue();
		
		ingrediente = new CardapioIngrediente();
		ingrediente.setEstoque(new Estoque());
		ingredientes = new ArrayList<CardapioIngrediente>();
		
		filtroEstoqueDTO = new FiltroEstoqueDTO();
	}
	
	public void selecionaEstoque(Estoque estoque){
		
		ingrediente.setEstoque(estoque);
		
		montaSubMedida();
	}
	
	public void montaSubMedida(){
		
		Estoque estoque = ingrediente.getEstoque();
		
		Medida medida = estoque.getMedida();
		Medida subMedida = ingrediente.getMedida();
		
		if (medida != null){
			
			if (subMedida == null && !Medida.isSubMedida(medida, subMedida)){
				
				ingrediente.setMedida(medida);
			}
			
			filtroEstoqueDTO.subMedida(medida);
			
		}else{
			
			estoque.setMedidaReposicao(null);
		}
	}
	
	public void adicionarIngrediente() {
		
		try {
			
			validacao.adicionarIngrediente(ingrediente, ingredientes);
			
			CardapioIngrediente cardapioIngrediente = new CardapioIngrediente();
			
			cardapioIngrediente.setEstoque(ingrediente.getEstoque());
			cardapioIngrediente.setQuantidade(ingrediente.getQuantidade());
			cardapioIngrediente.setIndex(indexIngrediente);
			cardapioIngrediente.setCardapio(cardapio);
			cardapioIngrediente.setMedida(ingrediente.getMedida());
			
			ingredientes.add(indexIngrediente,cardapioIngrediente);
			
			++indexIngrediente;
			
			ingrediente = new CardapioIngrediente();
			
			calcularValorCardapio(ingredientes);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void calcularValorCardapio(List<CardapioIngrediente> listaIngredientes) {
		
		double valorTotalCardapio = 0.0;
		
		for (CardapioIngrediente ingrediente : listaIngredientes){
			
			Estoque estoque = ingrediente.getEstoque();
			
			if (ingrediente.getMedida().equals(estoque.getMedida())){
				
				valorTotalCardapio += (ingrediente.getQuantidade() * estoque.getPreco());
			
			}else if (Medida.isSubMedida(estoque.getMedida(), ingrediente.getMedida())){
				
				//1000 significa a quantidade descontada da subMedida no caso 1 KG = 1000 G
				double converteQntParaSubMedida = ingrediente.getQuantidade() / 1000.00; 
				valorTotalCardapio += converteQntParaSubMedida * estoque.getPreco();
			}
		}
		
		//Valor sugerido, soma o valor de todos os ingredientes e adiciona 30%
		valorSugerido = DinheiroUtil.doubleEmReal(valorTotalCardapio * 1.3);
	}
	
	public void excluirIngrediente(CardapioIngrediente ingredienteSelecionado){
		
		ingredientes.remove(ingredienteSelecionado.getIndex());
		
		--indexIngrediente;
	}

	public CardapioIngrediente getIngrediente() {
		return ingrediente;
	}

	public List<CardapioIngrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<CardapioIngrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getIndexIngrediente() {
		return indexIngrediente;
	}

	public void setIndexIngrediente(int indexIngrediente) {
		this.indexIngrediente = indexIngrediente;
	}

	public Integer getIngredienteId() {
		return ingredienteId;
	}

	public void setIngredienteId(Integer ingredienteId) {
		this.ingredienteId = ingredienteId;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public FiltroEstoqueDTO getFiltroEstoqueDTO() {
		return filtroEstoqueDTO;
	}

	public String getValorSugerido() {
		return valorSugerido;
	}
}