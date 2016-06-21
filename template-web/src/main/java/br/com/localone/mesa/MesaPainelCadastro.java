package br.com.localone.mesa;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="painelMesaCadastro")
@ViewScoped
public class MesaPainelCadastro extends MesaPainelSuperController{
	
	private static final String WIDGET_VAR_CADASTRO_MESA = "cadastrarMesa";
	
	@EJB
	private MesaPainelCadatroValidacao mesaPainelCadatroValidacao;
	
	public void cadastrarMesa(){
		
		try {
			
			mesaPainelCadatroValidacao.validacao(mesa);
			
			service.salvar(mesa);
			inicio();
			
			fecharModal(WIDGET_VAR_CADASTRO_MESA);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_MESA_ADMIN;
	}
}