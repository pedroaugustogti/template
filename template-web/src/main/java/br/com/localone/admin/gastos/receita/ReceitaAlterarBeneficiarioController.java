package br.com.localone.admin.gastos.receita;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Receita;
import br.com.template.entidades.ReceitaSocio;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="alterarBeneficiario")
@ViewScoped
public class ReceitaAlterarBeneficiarioController extends ReceitaSuperController{
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		receita = new Receita();
		filtroReceitaDTO = new FiltroReceitaDTO();
		receita.setListSocio(new ArrayList<ReceitaSocio>());
	}

	public void cadastrar()  {
		
		try {
			
			validacaoReceita.valida(receita);
			service.salvar(receita);
			this.inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	};
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_RECEITA;
	}
}