package br.com.localone.admin.gastos.receita;

import java.util.List;

import javax.ejb.EJB;

import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Receita;
import br.com.template.entidades.Usuario;
import br.com.template.framework.AbstractManageBean;
import br.com.template.framework.GenericServiceController;

public abstract class ReceitaSuperController extends AbstractManageBean{
	
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
		
		usuarioSelecionado.setIndexSocio(indexSocio);
		usuarioSelecionado.setReceita(receita);
		
		receita.getListSocio().add(usuarioSelecionado);
		
		usuarioSelecionado = new Usuario();
		
		++indexSocio;
	}
	
	private boolean socioNaLista(List<Usuario> listSocios, Usuario socioSelecionado) {
		
		for (Usuario socio : listSocios){
			
			if (socio.getId().equals(socioSelecionado.getId())){
				
				return true;
			}
		}
		
		return false;
	}

	public void removerSocio(Usuario socio){
		
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