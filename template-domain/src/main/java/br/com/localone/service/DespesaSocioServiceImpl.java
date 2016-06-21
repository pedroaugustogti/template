package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroDespesaSocioDTO;
import br.com.template.entidades.DespesaSocio;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaSocioServiceImpl implements DespesaSocioService{
	
	@EJB
	private ConsultasDaoJpa<DespesaSocio> reposiroty;
	

	@Override
	public List<DespesaSocio> pesquisar(FiltroDespesaSocioDTO filtro, String... hibernateInitialize) {
		return reposiroty.filtrarPesquisa(filtro, DespesaSocio.class, hibernateInitialize);
	}
}