package br.com.localone.cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.CardapioService;
import br.com.localone.service.ComandaService;
import br.com.localone.service.PedidoService;
import br.com.template.domain.Situacao;
import br.com.template.domain.SituacaoPedido;
import br.com.template.domain.TipoProduto;
import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.dto.FiltroComandaDTO;
import br.com.template.dto.FiltroMenuDTO;
import br.com.template.dto.FiltroPedidoDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="menuCliente")
@ViewScoped
public class ClienteMenuController extends AbstractManageBean{
	
	private FiltroMenuDTO filtroMenuDTO;
	
	private Mesa mesa;
	
	private Cardapio cardapioSelecionado;
	private Integer quantidadeSelecionada;
	
	private boolean comandaLigada;
	
	private Comanda comanda;
	
	@EJB
	private CardapioService cardapioService;
	
	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private ComandaService comandaService;
	
	@EJB
	private GenericServiceController<Mesa, Long> service;
	
	@EJB
	private GenericServiceController<Comanda, Long> serviceComanda;
	
	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	private FiltroCardapioDTO filtroCardapioDTO;
	private List<Cardapio> listCardapio;
	private List<Pedido> listPedidos;
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		filtroMenuDTO = new FiltroMenuDTO();
		comanda = new Comanda();
		listPedidos = new ArrayList<Pedido>();
		
		recuperaMesaCliente();
		
		montaMenu();
		
		if (mesa.getComanda() != null && mesa.getComanda().getId() != null){
			
			comanda = mesa.getComanda();
			
			populaPedidosPorComanda(comanda.getId());
		}
	}

	public void atualizaComanda() {
		
		Long idComanda = comanda.getId();
				
		populaPedidosPorComanda(idComanda);
		
		comandaLigada = Boolean.TRUE;
	}
	
	private void populaPedidosPorComanda(Long idComanda) {
		
		FiltroPedidoDTO filtroPedido = new FiltroPedidoDTO();
		
		filtroPedido.setComandaId(idComanda);
		
		comanda.setListPedido(pedidoService.pesquisar(filtroPedido));
	}
	
	public void paraAutoRefreshComanda() {
		
		comandaLigada = Boolean.FALSE;
	}

	private void recuperaMesaCliente() throws NegocioException {
		
		Object obj = getAtributoSessao(AtributoSessao.OBJ_MESA);
		
		if (obj != null && obj instanceof Mesa){
			
			mesa = (Mesa) obj;
			
			controleRotativoMesa();
			
		}else{
			redirecionaPagina(Pagina.PAINEL_CLIENTE);
		}
	}

	//Metodo verifica se a mesa atual no banco de dados esta sincronizada com a mesa do cliente
	private void controleRotativoMesa() throws NegocioException {
		
		Mesa mesaAtual = service.getById(Mesa.class, mesa.getId());
		
		if (mesaAtual.getCodigo() == null || !mesaAtual.getCodigo().equals(mesa.getCodigo())){
			redirecionaPagina(Pagina.PAINEL_CLIENTE);
		}
	}
	
	private void montaMenu() {
		
		filtroCardapioDTO = new FiltroCardapioDTO();
		
		filtroCardapioDTO.setSituacao(Situacao.ATIVO);
		filtroCardapioDTO.setTipoProduto(TipoProduto.C);
		
		pesquisaCardapio();
	}

	public void pesquisaCardapio() {
		
		listCardapio = cardapioService.pesquisar(filtroCardapioDTO);
		
		for (Cardapio cardapio : listCardapio){
			
			for (Pedido pedido : listPedidos){
				
				if(cardapio.getId().equals(pedido.getCardapio().getId())){
					
					cardapio.setPedidoClienteMenu(Boolean.TRUE);
				}
			}
		}
	}

	public void adicionarPedido() throws NegocioException{
		
		controleRotativoMesa();
		
		Pedido pedido = new Pedido();
		
		pedido.setSituacao(SituacaoPedido.FILA);
		pedido.setHorarioSolicitacao(new Date());
		pedido.setCardapio(cardapioSelecionado);
		pedido.setComanda(comanda);
		pedido.setQuantidade(quantidadeSelecionada);
		
		listPedidos.add(pedido);
		
		limpaInstanciasPedido();
	}

	private void limpaInstanciasPedido() {
		quantidadeSelecionada = null;
		cardapioSelecionado = null;
	}

	public void removerPedido() throws NegocioException{
		
		listPedidos.remove(cardapioSelecionado);
	}
	
	public void visualizarComanda() throws NegocioException{
		
		this.atualizaComanda();
	}
	
	public void fecharComanda() throws NegocioException{
		
		this.paraAutoRefreshComanda();
	}
	
	public void finalizarPedido() throws NegocioException{
		
		montaComanda();
		
		vinculaComandaMesa();
		
		service.salvar(mesa);
		
		listPedidos = new ArrayList<Pedido>();
	}

	private void vinculaComandaMesa() {
		
		FiltroComandaDTO filtro = new FiltroComandaDTO();
		filtro.setMesaId(mesa.getId());
		
		comanda = comandaService.getComandaMesa(filtro);
		
		mesa.setComanda(comanda);
	}

	private void montaComanda() {
		
		comanda.setMesa(mesa);
		
		if (comanda.getListPedido() == null ){
			
			comanda.setListPedido(listPedidos);
		}else{
			
			comanda.getListPedido().addAll(listPedidos);
		}
		
		serviceComanda.salvar(comanda);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CLIENTE_MENU;
	}

	public FiltroMenuDTO getFiltroMenuDTO() {
		return filtroMenuDTO;
	}

	public void setFiltroMenuDTO(FiltroMenuDTO filtroMenuDTO) {
		this.filtroMenuDTO = filtroMenuDTO;
	}

	public FiltroCardapioDTO getFiltroCardapioDTO() {
		return filtroCardapioDTO;
	}

	public List<Cardapio> getListCardapio() {
		return listCardapio;
	}

	public Cardapio getCardapioSelecionado() {
		return cardapioSelecionado;
	}

	public void setCardapioSelecionado(Cardapio cardapioSelecionado) {
		this.cardapioSelecionado = cardapioSelecionado;
	}

	public Integer getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}

	public void setQuantidadeSelecionada(Integer quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}

	public List<Pedido> getListPedidos() {
		return listPedidos;
	}

	public boolean isComandaLigada() {
		return comandaLigada;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
}