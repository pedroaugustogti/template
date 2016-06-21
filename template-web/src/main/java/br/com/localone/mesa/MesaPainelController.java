package br.com.localone.mesa;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.entidades.Mesa;

@ManagedBean(name="painelMesa")
@ViewScoped
public class MesaPainelController extends MesaPainelSuperController{
	
	private List<Mesa> listMesa;
	
	public void pesquisar(){
		
		listMesa = mesaService.pesquisar(filtroMesaDTO);
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_MESA_ADMIN;
	}

	public List<Mesa> getListMesa() {
		return listMesa;
	}
}