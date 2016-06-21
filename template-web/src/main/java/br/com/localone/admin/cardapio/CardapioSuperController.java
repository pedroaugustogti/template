package br.com.localone.admin.cardapio;

import javax.ejb.EJB;

import br.com.template.domain.Medida;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.entidades.CardapioTempoPreparo;
import br.com.template.entidades.Estoque;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.DinheiroUtil;

public abstract class CardapioSuperController extends AbstractManageBean{
	
	protected Cardapio cardapio;

	@EJB
	protected GenericServiceController<Cardapio, Long> service;
	
	@EJB
	protected IngredientePainel ingredientePainel;
	
	@EJB
	protected CardapioValidacao cardapioValidacao;
	
	private String totalGasto;
	private String lucro;
	
	public void inicio(){
		
		cardapio = new Cardapio();
		cardapio.setTempoPreparo(new CardapioTempoPreparo());
		
		ingredientePainel.setCardapio(cardapio);
		ingredientePainel.inicio();
	}
	
	protected void calcularValorCardapio(Cardapio cardapio) {
		
		double valorTotalCardapio = 0.0;
		
		for (CardapioIngrediente ingrediente : cardapio.getListIngredientes()){
			
			Estoque estoque = ingrediente.getEstoque();
			
			if (ingrediente.getMedida().equals(estoque.getMedida())){
				
				valorTotalCardapio += (ingrediente.getQuantidade() * estoque.getPreco());
			
			}else if (Medida.isSubMedida(estoque.getMedida(), ingrediente.getMedida())){
				
				//1000 significa a quantidade descontada da subMedida no caso 1 KG = 1000 G
				double converteQntParaSubMedida = ingrediente.getQuantidade() / 1000.00; 
				valorTotalCardapio += converteQntParaSubMedida * estoque.getPreco();
			}
		}
		
		lucro = DinheiroUtil.doubleEmReal(cardapio.getPreco() - valorTotalCardapio);
		totalGasto = DinheiroUtil.doubleEmReal(valorTotalCardapio);
	}
	
	public Cardapio getCardapio() {
		return cardapio;
	}

	public IngredientePainel getIngredientePainel() {
		return ingredientePainel;
	}
	
	public String getTotalGasto() {
		return totalGasto;
	}

	public String getLucro() {
		return lucro;
	}
}