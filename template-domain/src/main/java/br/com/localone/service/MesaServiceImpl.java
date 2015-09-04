package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroMesaDTO;
import br.com.template.entidades.Mesa;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class MesaServiceImpl implements MesaService{
	
	@EJB
	private ConsultasDaoJpa<Mesa> reposiroty;

	@Override
	public List<Mesa> pesquisar(FiltroMesaDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Mesa.class);
	}
}