package br.com.localone.mesa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.AutorizacaoEnum;
import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.PedidoService;
import br.com.template.domain.Cargo;
import br.com.template.domain.Mensagem;
import br.com.template.domain.Situacao;
import br.com.template.domain.SituacaoMesa;
import br.com.template.domain.SituacaoPedido;
import br.com.template.entidades.Balanco;
import br.com.template.entidades.Comanda;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Mesa;
import br.com.template.entidades.Pedido;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.DinheiroUtil;

@ManagedBean(name="painelMesaFecharConta")
@ViewScoped
public class MesaPainelFecharContaController extends MesaPainelSuperController{
	
	private static final String WIDGET_VAR_CANCELAR_PEDIDO = "infoCancelamento";
	private static final String WIDGET_VAR_CONTA = "conta";
	
	private List<Pedido> listPedidos;
	
	private String valorTotalFormat;
	
	private String taxaServicoFormat;
	private boolean taxaServicoSelecionada;
	
	private boolean parcialSelecionado;
	private String parcialFormat;
	
	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private GenericServiceController<Comanda, Long> serviceComanda;
	
	@EJB
	private GenericServiceController<Pedido, Long> servicePedido;
	
	@EJB
	private GenericServiceController<Balanco, Long> serviceBalanco;

	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	private Pedido pedido;
	private double taxaServico;

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
			balanco.setFechamentoConta(new Date());
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
	
	public void cancelaPedido(){
		
		Funcionario funcionario = usuario().getFuncionario();
		
		if (!isGerente(funcionario)){
			
			contextPrimefaces().showMessageInDialog(facesMensagem(Mensagem.MNG024));
			return;
		}
		
		pedido.setGerente(funcionario);
		pedido.setSituacao(SituacaoPedido.CANCELADO);
		
		servicePedido.salvar(pedido);
		
		pedidosNaoCancelados();
		
		fecharModal(WIDGET_VAR_CANCELAR_PEDIDO);
		abrirModal(WIDGET_VAR_CONTA);
	}

	private boolean isGerente(Funcionario funcionario) {
		return funcionario != null && 
				funcionario.getSituacao().equals(Situacao.ATIVO) && 
				(funcionario.getCargo().equals(Cargo.GERENTE) || getAutorizacao().equals(AutorizacaoEnum.ADMINISTRADOR)) ;
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
}