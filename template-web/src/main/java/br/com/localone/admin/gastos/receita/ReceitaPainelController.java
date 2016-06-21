package br.com.localone.admin.gastos.receita;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ReceitaService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Bem;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.Receita;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.GenericServiceController;
import br.com.template.util.container.AtributoSessao;

@ManagedBean(name="painelReceita")
@ViewScoped
public class ReceitaPainelController extends ReceitaSuperController{
	
	private List<Receita> listReceita;
	private Receita receitaSelecionado;
	private Bem bemSelecionado;
	
	@EJB
	private ReceitaService receitaService;
	
	@EJB
	protected GenericServiceController<Bem, Long> serviceBem;
	
	@EJB
	private GenericServiceController<ConfigurarSocio, Long> serviceConfiguracaoSocio;
	
	@PostConstruct
	public void inicio(){
		
		filtroReceitaDTO = new FiltroReceitaDTO();
		bemSelecionado = new Bem();
		receitaSelecionado = new Receita();
	}
	
	public void pesquisar(){
		
		listReceita = receitaService.pesquisar(filtroReceitaDTO,"listBem", "listSocio");
	}
	
	public void verificaConfiguracaoSocio(){
		
		try {
			
			if (serviceConfiguracaoSocio.listarTodos(ConfigurarSocio.class).isEmpty()){
				
				enviaMensagem(Mensagem.MNG051);
				return;
			}
			
			redirecionaPagina(Pagina.CADASTRAR_RECEITA);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void redirecionaParaTelaAlterar(Receita receita) throws IOException, NegocioException{
		
		setAtributoSessao(AtributoSessao.OBJ_ALTERAR_USUARIO, receita);
		
		redirecionaPagina(Pagina.ALTERAR_USUARIO);
	}
	
	public void excluir(){
		service.excluir(receitaSelecionado);
		this.pesquisar();
	}
	
	public void venderBem(){
		
		try {
			
			validacaoReceita.validaVendaBem(bemSelecionado);
			serviceBem.salvar(bemSelecionado);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.PAINEL_RECEITA;
	}

	public List<Receita> getListReceita() {
		return listReceita;
	}

	public Receita getReceitaSelecionado() {
		return receitaSelecionado;
	}

	public void setReceitaSelecionado(Receita receitaSelecionado) {
		this.receitaSelecionado = receitaSelecionado;
	}

	public Bem getBemSelecionado() {
		return bemSelecionado;
	}

	public void setBemSelecionado(Bem bemSelecionado) {
		this.bemSelecionado = bemSelecionado;
	}
}