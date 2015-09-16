package br.com.localone.admin.gastos.socios;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;

@ManagedBean(name="painelSocio")
@ViewScoped
public class ConfigurarSocioController extends ConfigurarSocioSuperController{
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CONFIGURAR_SOCIOS;
	}
}