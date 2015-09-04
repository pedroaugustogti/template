package br.com.localone.admin.cardapio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroCardapio")
@ViewScoped
public class CardapioCadastroController extends CardapioSuperController{
	
	@PostConstruct
	public void inicio(){
		
		super.inicio();
	}

	public void cadastrar(){
		
		try {
			
			cardapio.setListIngredientes(ingredientePainel.getIngredientes());
			
			cardapioValidacao.valida(cardapio);
			
			service.salvar(cardapio);
			inicio();
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_CARDAPIO;
	}
}