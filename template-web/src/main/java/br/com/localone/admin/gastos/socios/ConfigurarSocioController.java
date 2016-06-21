package br.com.localone.admin.gastos.socios;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Situacao;
import br.com.template.entidades.Usuario;

@ManagedBean(name="painelSocio")
@ViewScoped
public class ConfigurarSocioController extends ConfigurarSocioSuperController{
	
	public void ativarSocio(Usuario usuario){
		
		usuario.setSituacao(Situacao.ATIVO);
	}
	
	public void inativarSocio(Usuario usuario){
		
		usuario.setSituacao(Situacao.INATIVO);
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CONFIGURAR_SOCIOS;
	}
}