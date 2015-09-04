package br.com.localone.cliente;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.MesaService;
import br.com.template.dto.FiltroMesaDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.entidades.Mesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelCliente")
@ViewScoped
public class ClientePainelController extends AbstractManageBean{
	
	private FiltroMesaDTO filtroMesaDTO;
	
	private String cpfCliente;
	
	@EJB
	private MesaService mesaService;
	
	@EJB
	private GenericServiceController<Mesa, Long> service;
	
	@EJB
	private GenericServiceController<Funcionario, Long> serviceFuncionario;
	
	@EJB
	private ClienteValidadorView validacaoView;
	
	@PostConstruct
	public void inicio(){
		
		filtroMesaDTO = new FiltroMesaDTO();
	}
	
	public void entrar() {
		
		List<Mesa> list = mesaService.pesquisar(filtroMesaDTO);
		
		try {
			
			validacaoView.validaCliente(list, cpfCliente);
			
			if (list.size() > 1){
				
				filtroMesaDTO.setCpfCliente(cpfCliente);
				list = mesaService.pesquisar(filtroMesaDTO);
			}
			
			Mesa mesa = list.listIterator().next();
			mesa.setCpfCliente(cpfCliente);
			service.salvar(mesa);
			
			inicio();
			
			setAtributoSessao(AtributoSessao.OBJ_MESA, mesa);
			redirecionaPagina(Pagina.CLIENTE_MENU);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_CLIENTE;
	}

	public FiltroMesaDTO getFiltroMesaDTO() {
		return filtroMesaDTO;
	}

	public void setFiltroMesaDTO(FiltroMesaDTO filtroMesaDTO) {
		this.filtroMesaDTO = filtroMesaDTO;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
}