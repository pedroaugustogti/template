package br.com.localone.mesa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.PedidoService;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Role;
import br.com.template.domain.SituacaoMesa;
import br.com.template.domain.SituacaoPedido;
import br.com.template.entidades.Balanco;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;
import br.com.template.entidades.Usuario;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.DinheiroUtil;

@ManagedBean(name="painelMesaFecharConta")
@ViewScoped
public class MesaPainelFecharContaController extends MesaPainelSuperController{
	
	private static final String WIDGET_VAR_CANCELAR_PEDIDO = "infoCancelamento";
	private static final String WIDGET_VAR_CONTA = "conta";
	
	private List<Pedido> listPedidos;
	
	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private GenericServiceController<Comanda, Long> serviceComanda;
	
	@EJB
	private GenericServiceController<Pedido, Long> servicePedido;
	
	@EJB
	private GenericServiceController<Balanco, Long> serviceBalanco;

	@EJB
	private GenericServiceController<Usuario, Long> serviceUsuario;
	
	private Pedido pedido;
	
	private Long idGerente;
	
	private double taxaServico;
	
	private String valorTotalFormat;
	private String taxaServicoFormat;
	private String parcialFormat;
	
	private boolean taxaServicoSelecionada;
	private boolean parcialSelecionado;
	

	@PostConstruct
	public void inicio(){
		
		pedido = new Pedido();
		zeraValorTotal();
	}

	public void montaListaPedidos(Mesa mesaSelecionada){
		
		super.mesa = service.getById(Mesa.class, mesaSelecionada.getId(),"funcionario","funcionario.pessoa","comanda","comanda.listPedido");
		
		if (mesa.getComanda() == null){
			
			listPedidos = new ArrayList<Pedido>();
			zeraValorTotal();
			return;
		}
		
		pedidosNaoCancelados();
		
		if (mesa.getComanda().getParcial() != null){
			
			parcialSelecionado = Boolean.TRUE;
		}
		
		taxaServicoSelecionada = Boolean.TRUE;
		calculoValorTotal(mesa);
	}

	private void pedidosNaoCancelados() {
		
		listPedidos = pedidoService.pedidosNaoCancelados(mesa.getComanda());
	}
	
	public void fecharConta(){
		
		if (listPedidos != null && !listPedidos.isEmpty()){
			preencheBalanco();
		}
		
		limpaMesa();
	}

	private void zeraValorTotal() {
		
		taxaServicoFormat = DinheiroUtil.doubleEmReal(0.0);
		valorTotalFormat = DinheiroUtil.doubleEmReal(0.0);
	}
	
	private void preencheBalanco() {
		
		for (Pedido pedido: listPedidos){
			
			Balanco balanco = new Balanco();
			
			Integer numMesa = mesa.getNumMesa();
			Funcionario garcom = mesa.getFuncionario();
			
			balanco.setCardapio(pedido.getCardapio());
			balanco.setGarcom(garcom);
			balanco.setCozinheiro(pedido.getCozinheiro());
			balanco.setHorarioSolicitacao(pedido.getHorarioSolicitacao());
			balanco.setHorarioConclusao(pedido.getHorarioConclusao());
			balanco.setNumMesa(numMesa);
			balanco.setValorConta(calculoValorTotal(mesa));
			balanco.setFechamentoConta(Calendar.getInstance());
			balanco.setCpf(mesa.getCpfCliente());
			balanco.setTaxaServico(taxaServico);
			
			serviceBalanco.salvarSemMensagem(balanco);
		}
	}

	private double calculoValorTotal(Mesa mesa) {
		
		final double DEZ_PORCENTO = 0.1;
		
		double valorTotal = 0.0;
		taxaServico = 0.0;
		Double parcial = mesa.getComanda().getParcial();
		
		for (Pedido pedido: mesa.getComanda().getListPedido()){
			
			valorTotal += pedido.getCardapio().getPreco();
		}
		
		if (parcial != null){
			
			valorTotal -= parcial;
			
			parcialFormat = DinheiroUtil.doubleEmReal(parcial);
		}
		
		if (taxaServicoSelecionada){
			
			taxaServico = valorTotal * DEZ_PORCENTO;
			
		}else{
			taxaServico = 0.0;
		}
		
		valorTotal += taxaServico;
		
		taxaServicoFormat = DinheiroUtil.doubleEmReal(taxaServico);
		valorTotalFormat = DinheiroUtil.doubleEmReal(valorTotal);
		
		return valorTotal;
	}

	public void taxaServico(){
		
		calculoValorTotal(mesa);
	}
	
	public void parcial(){
		
//		if (!parcialSelecionado){
//			
//			super.mesa.getComanda().setParcial(null);
//			super.mesa = service.merge(mesa);
//			
//			calculoValorTotal(super.mesa);
//		}
	}
	
	public void adicionarParcial(){
		
//		if (StringUtils.isBlank(parcialFormat)){
//			
//			contextPrimefaces().showMessageInDialog(facesMensagem(Mensagem.MNG027));
//			return;
//		}
//		
//		mesa.getComanda().setParcial(DinheiroUtil.realParaDouble(parcialFormat));
//		super.mesa = service.merge(mesa);
//		parcialFormat = StringUtils.EMPTY;
//		
//		calculoValorTotal(mesa);
	}
	
	public void cancelaPedido(Long idUsuario){
		
		Usuario usuario = serviceUsuario.getById(Usuario.class, idUsuario);
		Role roleGerente = usuario.getRole();
		
		if (roleGerente == null || !Role.GERENTE.equals(roleGerente)){
			
			contextPrimefaces().showMessageInDialog(facesMensagem(Mensagem.MNG024));
			return;
		}
		
		pedido.setSituacao(SituacaoPedido.CANCELADO);
		
		servicePedido.salvar(pedido);
		
		pedidosNaoCancelados();
		
		fecharModal(WIDGET_VAR_CANCELAR_PEDIDO);
		abrirModal(WIDGET_VAR_CONTA);
	}

	private void limpaMesa() {
		
		mesa.setCodigo(null);
		mesa.setCpfCliente(null);
		mesa.setComanda(null);
		mesa.setFuncionario(null);
		mesa.setSituacaoMesa(SituacaoMesa.LIVRE);
		
		service.salvar(mesa);
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_MESA_ADMIN;
	}

	public boolean isTaxaServicoSelecionada() {
		return taxaServicoSelecionada;
	}

	public void setTaxaServicoSelecionada(boolean taxaServicoSelecionada) {
		this.taxaServicoSelecionada = taxaServicoSelecionada;
	}

	public List<Pedido> getListPedidos() {
		return listPedidos;
	}

	public String getValorTotalFormat() {
		return valorTotalFormat;
	}

	public String getTaxaServicoFormat() {
		return taxaServicoFormat;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isParcialSelecionado() {
		return parcialSelecionado;
	}

	public void setParcialSelecionado(boolean parcialSelecionado) {
		this.parcialSelecionado = parcialSelecionado;
	}

	public String getParcialFormat() {
		return parcialFormat;
	}

	public void setParcialFormat(String parcialFormat) {
		this.parcialFormat = parcialFormat;
	}

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}
}