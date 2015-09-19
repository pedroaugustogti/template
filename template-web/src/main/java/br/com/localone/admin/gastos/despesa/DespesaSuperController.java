package br.com.localone.admin.gastos.despesa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class DespesaSuperController extends AbstractManageBean {
	
	@EJB
	protected GenericServiceController<Despesa, Long> service;
	
	@EJB
	protected DespesaValidacao despesaValidacao;
	
	protected FiltroDespesaDTO filtroDespesaDTO;
	
	private Usuario usuarioSelecionado;
	 
	protected Despesa despesa;
	protected DespesaSocio despesaSocio;
	
	private int indexDespesaSocio;
	
	@PostConstruct
	public void inicio(){
		
		despesa = new Despesa();
		despesaSocio = new DespesaSocio();
		filtroDespesaDTO = new FiltroDespesaDTO();
	}
	
	public void adicionarSocio(){
		
		if(socioNaLista(despesa.getListDespesaSocio(), usuarioSelecionado)){
			
			enviaMensagem(Mensagem.MNG045);
			return;
			
		}else if (despesa.getListDespesaSocio() == null){
			
			despesa.setListDespesaSocio(new ArrayList<DespesaSocio>());
		}
		
		despesaSocio.setSocio(usuarioSelecionado);
		despesaSocio.setIndex(indexDespesaSocio);
		despesaSocio.setDespesa(despesa);
		
		despesa.getListDespesaSocio().add(despesaSocio.getIndex(), despesaSocio);
		
		despesaSocio = new DespesaSocio();
		
		++indexDespesaSocio;
	}
	
	private boolean socioNaLista(List<DespesaSocio> listSocios, Usuario socioSelecionado) {
		
		if (listSocios == null || listSocios.isEmpty()){
			return false;
		}
		
		for (DespesaSocio despesaSocio : listSocios){
			
			if (despesaSocio.getSocio().getId().equals(socioSelecionado.getId())){
				
				return true;
			}
		}
		
		return false;
	}

	public void removerSocio(DespesaSocio despesaSocio){
		
		despesa.getListDespesaSocio().remove(despesaSocio.getIndex());
		
		--indexDespesaSocio;
	}
	
	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public FiltroDespesaDTO getFiltroDespesaDTO() {
		return filtroDespesaDTO;
	}

	public void setFiltroDespesaDTO(FiltroDespesaDTO filtroDespesaDTO) {
		this.filtroDespesaDTO = filtroDespesaDTO;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public DespesaSocio getDespesaSocio() {
		return despesaSocio;
	}

	public void setDespesaSocio(DespesaSocio despesaSocio) {
		this.despesaSocio = despesaSocio;
	}
}