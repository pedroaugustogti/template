package br.com.localone.admin.cardapio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.CardapioIngrediente;
import br.com.template.excecao.NegocioException;
import br.com.template.util.HibernateUtil;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarCardapio")
@ViewScoped
public class CardapioAlterarController extends CardapioSuperController{
	
	@PostConstruct
	public void obtemCardapioSelecionado() throws NegocioException{ 
		
		Object dadosCardapio = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_CARDAPIO);
		
		if (dadosCardapio != null && dadosCardapio instanceof Cardapio){
			
			super.inicio();
			
			cardapio = service.getById(Cardapio.class, ((Cardapio) dadosCardapio).getId(), "listIngredientes", "listIngredientes.estoque");
			ingredientePainel.setCardapio(cardapio);
			
			montaGridComIndex(cardapio.getListIngredientes());
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}

	@SuppressWarnings("unchecked")
	private void montaGridComIndex(List<CardapioIngrediente> listIngredientes) {
		
		listIngredientes = (List<CardapioIngrediente>) HibernateUtil.getObjetoInicializado(listIngredientes);

		for (CardapioIngrediente cardapioIngrediente : listIngredientes){
			
			cardapioIngrediente.setIndex(listIngredientes.indexOf(cardapioIngrediente));
		}
		
		ingredientePainel.setIndexIngrediente(listIngredientes.size());
		ingredientePainel.setIngredientes(listIngredientes);
	}

	public void alterar() {
		
		try {
			
			cardapio.setListIngredientes(ingredientePainel.getIngredientes());
			
			cardapioValidacao.valida(cardapio);
			
			service.salvar(cardapio);
			redirecionaPagina(Pagina.PAINEL_CARDAPIO);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_CARDAPIO);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_CARDAPIO;
	}
}