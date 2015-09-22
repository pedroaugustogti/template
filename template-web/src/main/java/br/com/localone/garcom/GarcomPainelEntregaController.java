package br.com.localone.garcom;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.config.Config;
import br.com.localone.config.ConfigProperties;
import br.com.localone.service.PedidoService;
import br.com.template.domain.SituacaoPedido;
import br.com.template.entidades.Pedido;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

@ManagedBean(name="painelGarcomEntrega")
@SessionScoped
public class GarcomPainelEntregaController extends AbstractManageBean{
	
	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private GenericServiceController<Pedido, Long> servicePedido;
	
	private List<Pedido> listPedidosConcluidos;
	
	private String threadRefresh;
	
	@PostConstruct
	public void inicio(){
		
		threadRefresh = ConfigProperties.getValue(Config.THREAD_ENTREGA_PEDIDO);
		iniciaMotorPainel();
	}
	
	public void efetuarEntregaPedido(Pedido pedido){
		
		pedido.setSituacao(SituacaoPedido.ENTREGUE);
		
		servicePedido.salvarSemMensagem(pedido);
	}
	
	private void iniciaMotorPainel() {
		
		paraThread = Boolean.FALSE;
		
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				while (true){
					
					listPedidosConcluidos = pedidoService.pedidosDoDia(SituacaoPedido.CONCLUIDO);
					
					try {
						Thread.sleep(Long.valueOf(threadRefresh));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (paraThread){
						break;
					}
				}
			}
		};
		
		new Thread(thread).start();
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_ENTREGA_GARCOM;
	}

	public List<Pedido> getListPedidosConcluidos() {
		return listPedidosConcluidos;
	}

	public String getThreadRefresh() {
		return threadRefresh;
	}
}