package br.com.localone.admin.gastos.socios;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.localone.service.ConfigurarSocioService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroConfigurarSocioDTO;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.Pessoa;
import br.com.template.entidades.QuotaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class ConfigurarSocioSuperController extends AbstractManageBean{
	
	public static final int CEM_PORCENTO = 100;
	
	private Usuario usuarioSelecionado;
	
	protected QuotaSocio quotaSocio;
	
	protected ConfigurarSocio configurarSocio;
	
	protected FiltroConfigurarSocioDTO FiltroConfigurarSocioDTO;
	
	@EJB
	protected GenericServiceController<ConfigurarSocio, Long> service;
	
	@EJB
	protected ConfigurarSocioValidacao configurarSocioValidacao;
	
	@EJB
	protected ConfigurarSocioService configurarSocioService;
	
	@PostConstruct
	public void inicio(){
		
		configurarSocio = new ConfigurarSocio();
		quotaSocio = new QuotaSocio();
		FiltroConfigurarSocioDTO = new FiltroConfigurarSocioDTO();
	}
	
	private void limpaUsuarioSelecionado(){
		
		usuarioSelecionado = new Usuario();
		Pessoa pessoa = new Pessoa();
		usuarioSelecionado.setPessoa(pessoa);
	}
	
	public void pesquisaConfiguracaoSociosPorEmpresa(){
		
		try {
			
			limpaUsuarioSelecionado();
			configurarSocioValidacao.naoExisteUsuarioAdministrador(configurarSocio.getEmpresa());
			
			montaConfiguracao();
			
		} catch (NegocioException e) {
			e.printStackTrace();
			inicio();
		}
	}

	private void montaConfiguracao() throws NegocioException {
		
		ConfigurarSocio conf = configurarSocio();
		
		if (conf != null){
			
			configurarSocio = conf;
			
			adicionaIndexPorQuotaSocio();
		}
	}

	private void adicionaIndexPorQuotaSocio() {
		
		for (int index=0; index < configurarSocio.getListQuotaSocio().size(); index++){
			
			QuotaSocio quota = configurarSocio.getListQuotaSocio().get(index);
			quota.setIndex(index);
		}
	}

	public void adicionarSocio() {
		
		try {
			
			configurarSocioValidacao.camposObrigatorios(quotaSocio);

			inicializaConcifugracao();
			
			configurarSocioValidacao.socioNaLista(configurarSocio.getListQuotaSocio(), usuarioSelecionado);
				
			if (configurarSocio.getListQuotaSocio() == null){
				
				configurarSocio.setListQuotaSocio(new ArrayList<QuotaSocio>());
			}
			
			configurarSocioValidacao.verificaQuotaDisponivelParaEmpresa(configurarSocio, quotaSocio);
			
			quotaSocio.setSocio(usuarioSelecionado);
			quotaSocio.setConfigurarSocio(configurarSocio);
			configurarSocio.getListQuotaSocio().add(quotaSocio);
			
			quotaSocio = new QuotaSocio();
			
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	private void inicializaConcifugracao() throws NegocioException {
		
		if (configurarSocio.getId() == null){
			
			ConfigurarSocio config = this.configurarSocio();
			
			if (config != null){
				
				configurarSocio = config;
			}
		}
	}
	
	public void cadastrar(){
		
		if (configurarSocioValidacao.somaQuotasPorConfiguracao(configurarSocio) == CEM_PORCENTO){
			
			service.salvar(configurarSocio);
		}else{
			
			enviaMensagem(Mensagem.MNG054);
		}
	}

	private ConfigurarSocio configurarSocio() throws NegocioException {
		return configurarSocioService.configuracaoPorEmpresa(configurarSocio.getEmpresa());
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public ConfigurarSocio getConfigurarSocio() {
		return configurarSocio;
	}

	public void setConfigurarSocio(ConfigurarSocio configurarSocio) {
		this.configurarSocio = configurarSocio;
	}

	public QuotaSocio getQuotaSocio() {
		return quotaSocio;
	}

	public void setQuotaSocio(QuotaSocio quotaSocio) {
		this.quotaSocio = quotaSocio;
	}

	public FiltroConfigurarSocioDTO getFiltroConfigurarSocioDTO() {
		return FiltroConfigurarSocioDTO;
	}
}