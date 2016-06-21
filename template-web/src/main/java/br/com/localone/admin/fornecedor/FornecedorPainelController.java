package br.com.localone.admin.fornecedor;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.FornecedorService;
import br.com.template.dto.FiltroFornecedorDTO;
import br.com.template.entidades.Fornecedor;
import br.com.template.excecao.NegocioException;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelFornecedor")
@ViewScoped
public class FornecedorPainelController extends FornecedorSuperController{
	
	private List<Fornecedor> listFornecedor;
	private Fornecedor fornecedorSelecionado;
	
	@EJB
	private FornecedorService fornecedorService;
	
	@PostConstruct
	public void inicio(){
		
		filtroFornecedorDTO = new FiltroFornecedorDTO();
		fornecedorSelecionado = new Fornecedor();
	}
	
	public void pesquisar(){
		
		listFornecedor = fornecedorService.pesquisar(filtroFornecedorDTO,"listProdutos");
	}
	
	public void redirecionaParaTelaAlterar(Fornecedor fornecedor) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_FORNECEDOR, fornecedor);
		
		redirecionaPagina(Pagina.ALTERAR_FORNECEDOR);
	}
	
	public void excluir(){
		service.excluir(fornecedorSelecionado);
		this.pesquisar();
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_FORNECEDOR;
	}

	public List<Fornecedor> getListFornecedor() {
		return listFornecedor;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}
}