package br.com.localone.mesa;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.localone.service.MesaService;
import br.com.template.dto.FiltroMesaDTO;
import br.com.template.entidades.Mesa;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class MesaPainelSuperController extends AbstractManageBean{
	
	protected Mesa mesa;
	
	protected FiltroMesaDTO filtroMesaDTO;
	
	@EJB
	protected MesaService mesaService;
	
	@EJB
	protected GenericServiceController<Mesa, Long> service;
	
	@PostConstruct
	public void inicio(){
		
		mesa = new Mesa();
		filtroMesaDTO = new FiltroMesaDTO();
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public FiltroMesaDTO getFiltroMesaDTO() {
		return filtroMesaDTO;
	}
}