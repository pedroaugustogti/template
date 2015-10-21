package br.com.localone.admin.gastos.despesa;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
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
		
		try {
			
			despesaValidacao.socioNaLista(despesa.getListDespesaSocio(), usuarioSelecionado);
			
			if (despesa.getListDespesaSocio() == null){
				
				despesa.setListDespesaSocio(new ArrayList<DespesaSocio>());
			}
			
			despesaSocio.setSocio(usuarioSelecionado);
			despesaSocio.setIndex(indexDespesaSocio);
			despesaSocio.setDespesa(despesa);
			
			despesaValidacao.camposObrigatoriosSociosPagantes(despesaSocio);
			
			despesa.getListDespesaSocio().add(despesaSocio.getIndex(), despesaSocio);
			
			despesaSocio = new DespesaSocio();
			
			++indexDespesaSocio;
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
			
		
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