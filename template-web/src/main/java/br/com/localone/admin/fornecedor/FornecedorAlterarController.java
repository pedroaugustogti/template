package br.com.localone.admin.fornecedor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Fornecedor;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="alterarFornecedor")
@ViewScoped
public class FornecedorAlterarController extends FornecedorSuperController{
	
	@PostConstruct
	public void inicio() throws NegocioException{
		
		Object dadosFornecedor = getAtributoSessao(AtributoSessao.OBJ_ALTERAR_FORNECEDOR);
		
		if (dadosFornecedor != null && dadosFornecedor instanceof Fornecedor){
			
			fornecedor = (Fornecedor) dadosFornecedor;
			adicionaIndexProduto(fornecedor);
		}else{
			throw new NegocioException(Mensagem.MEI010);
		}
	}

	public void alterar() throws NegocioException{
		
		try {
			
			validacaoFornecedor.valida(fornecedor);
			service.salvar(fornecedor);
			
			inicio();
			
			redirecionaPagina(Pagina.PAINEL_FORNECEDOR);
			limparAtributoDaSessao(AtributoSessao.OBJ_ALTERAR_FORNECEDOR);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.ALTERAR_FORNECEDOR;
	}
}