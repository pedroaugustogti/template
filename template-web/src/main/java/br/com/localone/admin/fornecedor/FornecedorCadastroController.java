package br.com.localone.admin.fornecedor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.dto.FiltroFornecedorDTO;
import br.com.template.entidades.Fornecedor;
import br.com.template.excecao.NegocioException;

@ManagedBean(name="cadastroFornecedor")
@ViewScoped
public class FornecedorCadastroController extends FornecedorSuperController{
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		fornecedor = new Fornecedor();
		filtroFornecedorDTO = new FiltroFornecedorDTO();
	}
	
	public void cadastrar()  {
		
		try {
			
			validacaoFornecedor.valida(fornecedor);
			service.salvar(fornecedor);
			this.inicio();
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	};
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.CADASTRAR_FORNECEDOR;
	}
}