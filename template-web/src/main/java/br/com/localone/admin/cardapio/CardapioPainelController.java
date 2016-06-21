package br.com.localone.admin.cardapio;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.CardapioService;
import br.com.template.domain.Situacao;
import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelCardapio")
@ViewScoped
public class CardapioPainelController extends CardapioSuperController {
	
	private List<Cardapio> listCardapio;
	private FiltroCardapioDTO filtroCardapioDTO;
	
	@EJB
	private CardapioService cardapioService;
	
	@PostConstruct
	public void inicio(){
		
		filtroCardapioDTO = new FiltroCardapioDTO();
	}
	
	public void pesquisar(){
		
		listCardapio = cardapioService.pesquisar(filtroCardapioDTO);
	}
	
	public void selecionaCardapio(Cardapio carda){
		
		super.cardapio = carda = service.getById(Cardapio.class, carda.getId(), "listIngredientes", "listIngredientes.estoque");
		
		calcularValorCardapio(cardapio);
	}
	
	public void redirecionaParaTelaAlterar(Cardapio cardapio) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_CARDAPIO, cardapio);
		redirecionaPagina(Pagina.ALTERAR_CARDAPIO);
	}
	
	public void excluir(){
		service.excluir(cardapio);
		this.pesquisar();
	}
	
	public void ativarCardapio(Cardapio cardapio){
		
		cardapioValidacao.verificaTodosIngredientesEmEstoque(cardapio);
		
		cardapio.setSituacao(Situacao.ATIVO);
		service.salvar(cardapio);
	}
	
	public void inativarCardapio(Cardapio cardapio){
		
		cardapio.setSituacao(Situacao.INATIVO);
		service.salvar(cardapio);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_CARDAPIO;
	}

	public List<Cardapio> getListCardapio() {
		return listCardapio;
	}

	public FiltroCardapioDTO getFiltroCardapioDTO() {
		return filtroCardapioDTO;
	}

	public void setFiltroCardapioDTO(FiltroCardapioDTO filtroCardapioDTO) {
		this.filtroCardapioDTO = filtroCardapioDTO;
	}
}