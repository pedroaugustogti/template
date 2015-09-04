package br.com.localone.cozinha;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.config.Config;
import br.com.localone.config.ConfigProperties;
import br.com.localone.service.PedidoService;
import br.com.localone.service.UsuarioService;
import br.com.template.domain.SituacaoPedido;
import br.com.template.entidades.Ocio;
import br.com.template.entidades.Pedido;
import br.com.template.entidades.TempoOcioso;
import br.com.template.entidades.Usuario;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.DataUtil;

@ManagedBean(name="painelCozinha")
@SessionScoped
public class CozinhaPainelController extends AbstractManageBean{
	
	private static final int UM_MINUTO_EM_MILISEGUNDOS = 1000 * 60;  
	
	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private GenericServiceController<Pedido, Long> servicePedido;
	
	@EJB
	private CozinhaPainelControleEstoque controleEstoque;
	
	@EJB
	private GenericServiceController<TempoOcioso, Long> serviceTempoOcioso;
	
	private List<Pedido> listPedidoPrioritario;
	private List<Pedido> listProximosPedidos;
	private List<Pedido> listPedidoEmPreparo;
	private List<Pedido> listPedidoFinalizado;
	
	private Usuario usuario;

	private String threadRefresh;
	
	private long tempoInicioOcioso;
	private long tempoOcioso;
	private String tempoOciosoFormat;
	private Pedido pedidoAguardandoInicioPreparo;
	
	@PostConstruct
	public void inicio(){
		
		threadRefresh = ConfigProperties.getValue(Config.THREAD_ENTREGA_PEDIDO);
		
		usuario = usuario();
		
		iniciaMotorPainel();
		
	}
	
	public void iniciarPreparo(Pedido pedido){
		
		pedido.setSituacao(SituacaoPedido.PREPARO);
		pedido.setCozinheiro(usuario.getFuncionario());
		
		servicePedido.salvar(pedido);
		
		controleEstoque.reduzEstoque(pedido);
	}
	
	public void finalizarPreparo(Pedido pedido){
		
		pedido.setSituacao(SituacaoPedido.CONCLUIDO);
		pedido.setHorarioConclusao(new Date());
		
		servicePedido.salvar(pedido);
	}
	
	public void cancelarPedido(Pedido pedido){
		
		pedido.setSituacao(SituacaoPedido.CANCELADO);
		
		servicePedido.salvar(pedido);
	}

	private void iniciaMotorPainel() {
		
		if (getAutorizacao() != null){
			new Thread(getThreadPainelAutomatico()).start();
		}
	}

	private Runnable getThreadPainelAutomatico() {
		
		paraThread = Boolean.FALSE;
		tempoInicioOcioso = System.currentTimeMillis();
		
		return new Runnable() {
			
			@Override
			public void run() {
				
				while (true){
				
					List<Pedido> pedidosNaFila = pedidoService.pedidosDoDia(SituacaoPedido.FILA);
					
					if (!pedidosNaFila.isEmpty()){
						
						Pedido primeiroPedidoDaFila = pedidosNaFila.iterator().next();
						
						calculaTempoOcioso(primeiroPedidoDaFila);
						
						listPedidoPrioritario = new ArrayList<Pedido>();
						listPedidoPrioritario.add(primeiroPedidoDaFila);
						
						listProximosPedidos = new ArrayList<Pedido>(pedidosNaFila);
						listProximosPedidos.remove(primeiroPedidoDaFila);
						
					}else{
						listPedidoPrioritario = new ArrayList<Pedido>(0);
						listProximosPedidos = new ArrayList<Pedido>(0);
					}
					
					listPedidoEmPreparo = pedidoService.pedidosDoDia(SituacaoPedido.PREPARO);
					
					if (!listPedidoEmPreparo.isEmpty()){
						tempoOcioso = 0;
						tempoInicioOcioso = System.currentTimeMillis();
						formataTempoOcioso(tempoOcioso);
					}
					
					listPedidoFinalizado = pedidoService.pedidosDoDia(SituacaoPedido.CONCLUIDO);
					
					try {
						Thread.sleep(Long.valueOf(threadRefresh));
					} catch (InterruptedException e) {
						
						System.out.println("Thread do painel da cozinha interrompido");
					}
					
					if (paraThread){
						break;
					}
				}
			}

			private void calculaTempoOcioso(Pedido primeiroPedidoDaFila) {
				
				long tempoAtual = System.currentTimeMillis();
				
				if ( pedidoAguardandoInicioPreparo == null || 
						primeiroPedidoDaFila.getId().equals(pedidoAguardandoInicioPreparo.getId()) || tempoOcioso == 0){
					
					tempoOcioso = tempoAtual - tempoInicioOcioso;
					pedidoAguardandoInicioPreparo = primeiroPedidoDaFila;
					
					verificaTempoOciosoAcimaUmMinuto(tempoOcioso);
				}
				
				formataTempoOcioso(tempoOcioso);
			}

			private void verificaTempoOciosoAcimaUmMinuto(long tempoOcioso) {
				
				if (tempoOcioso >= UM_MINUTO_EM_MILISEGUNDOS){
					
					TempoOcioso tempo = new TempoOcioso();
					Ocio ocio = new Ocio();
					
					tempo.setCozinheiro(usuario.getFuncionario());
					tempo.setHorario(new Date());
					
					ocio.setMinutoOcioso(tempoOcioso / 1000);
					tempo.setOcio(ocio);
					
					serviceTempoOcioso.salvar(tempo);
				}
			}
		};
	}
	
	private void formataTempoOcioso(long tempoOcioso) {
		
		tempoOciosoFormat = DataUtil.converteMilisegundosEmTempoFormatado(tempoOcioso);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_COZINHA;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public List<Pedido> getListPedidoPrioritario() {
		return listPedidoPrioritario;
	}

	public List<Pedido> getListProximosPedidos() {
		return listProximosPedidos;
	}

	public List<Pedido> getListPedidoEmPreparo() {
		return listPedidoEmPreparo;
	}

	public List<Pedido> getListPedidoFinalizado() {
		return listPedidoFinalizado;
	}

	public String getThreadRefresh() {
		return threadRefresh;
	}

	public String getTempoOciosoFormat() {
		return tempoOciosoFormat;
	}
}