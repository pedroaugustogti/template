package br.com.localone.admin.gastos.socios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.localone.service.ConfigurarSocioService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroConfigurarSocioDTO;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.QuotaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class ConfigurarSocioSuperController extends AbstractManageBean{
	
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
	
	public void pesquisaConfiguracaoSociosPorEmpresa(){
		
		try {
			
			configurarSocio = configurarSocioService.configuracaoPorEmpresa(configurarSocio.getEmpresa());
			
			if (configurarSocio == null){
				
				configurarSocio = new ConfigurarSocio();
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarSocio(){
		
		if(socioNaLista(configurarSocio.getListQuotaSocio(), usuarioSelecionado)){
			
			enviaMensagem(Mensagem.MNG045);
			return;
			
		}else if (configurarSocio.getListQuotaSocio() == null){
			
			configurarSocio.setListQuotaSocio(new ArrayList<QuotaSocio>());
		}
		
		quotaSocio.setSocio(usuarioSelecionado);
		quotaSocio.setConfigurarSocio(configurarSocio);
		
		configurarSocio.getListQuotaSocio().add(quotaSocio);
		
		quotaSocio = new QuotaSocio();
		
		service.salvar(configurarSocio);
	}
	
	private boolean socioNaLista(List<QuotaSocio> listSocios, Usuario socioSelecionado) {
		
		if (listSocios == null || listSocios.isEmpty()){
			return false;
		}
		
		for (QuotaSocio configurarSocio : listSocios){
			
			if (configurarSocio.getSocio().getId().equals(socioSelecionado.getId())){
				
				return true;
			}
		}
		
		return false;
	}

	public void removerSocio(QuotaSocio quotaSocio){
		
		configurarSocio.getListQuotaSocio().remove(quotaSocio);
		
		service.salvar(configurarSocio);
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