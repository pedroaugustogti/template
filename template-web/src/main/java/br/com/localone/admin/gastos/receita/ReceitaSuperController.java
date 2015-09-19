package br.com.localone.admin.gastos.receita;

import java.util.List;

import javax.ejb.EJB;

import br.com.localone.admin.gastos.socios.ConfiguracaoSocioSuperController;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Receita;
import br.com.template.entidades.ReceitaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.framework.GenericServiceController;

public abstract class ReceitaSuperController extends ConfiguracaoSocioSuperController{
	
	@EJB
	protected GenericServiceController<Receita, Long> service;
	
	@EJB
	protected ReceitaValidadorView validacaoReceita;
	
	protected FiltroReceitaDTO filtroReceitaDTO;
	
	private Usuario usuarioSelecionado;
	
	protected Receita receita;
	
	private int indexSocio;
	
	public void adicionarSocio(){
		
		if(socioNaLista(receita.getListSocio(), usuarioSelecionado)){
			
			enviaMensagem(Mensagem.MNG045);
			return;
		}
		
		ReceitaSocio receitaSocio = new ReceitaSocio();
		receitaSocio.setReceita(receita);
		receitaSocio.setSocio(usuarioSelecionado);
		receitaSocio.setIndexSocio(indexSocio);
		
		receita.getListSocio().add(receitaSocio);
		
		usuarioSelecionado = new Usuario();
		
		++indexSocio;
	}
	
	private boolean socioNaLista(List<ReceitaSocio> listSocios, Usuario socioSelecionado) {
		
		for (ReceitaSocio socio : listSocios){
			
			if (socio.getSocio().getId().equals(socioSelecionado.getId())){
				
				return true;
			}
		}
		
		return false;
	}

	public void removerSocio(ReceitaSocio socio){
		
		receita.getListSocio().remove(socio.getIndexSocio());
		
		--indexSocio;
	}
	
	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public FiltroReceitaDTO getFiltroReceitaDTO() {
		return filtroReceitaDTO;
	}

	public void setFiltroReceitaDTO(FiltroReceitaDTO filtroReceitaDTO) {
		this.filtroReceitaDTO = filtroReceitaDTO;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}