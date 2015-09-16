package br.com.localone.admin.gastos.receita;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Bem;
import br.com.template.entidades.Receita;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroReceita")
@ViewScoped
public class ReceitaCadastroController extends ReceitaSuperController{
	
	private Bem bem;
	
	private int indexBem;
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		receita = new Receita();
		filtroReceitaDTO = new FiltroReceitaDTO();
		limpaBem();
		receita.setListSocio(new ArrayList<Usuario>());
	}

	private void limpaBem() {
		bem = new Bem();
		bem.setReceita(receita);
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
	
	public void adicionarBem(){
		
		try {
			
			validacaoReceita.validaAdicionarBem(bem);
			
			if (receita.getListBem() == null){
				
				receita.setListBem(new ArrayList<Bem>());
			}
			
			bem.setIndex(indexBem);
			receita.getListBem().add(indexBem, bem);
			
			limpaBem();
			
			++indexBem;
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void removerBem(Bem bem){
		
		receita.getListBem().remove(bem.getIndex());
		
		--indexBem;
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_RECEITA;
	}

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}
}