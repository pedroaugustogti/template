package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Receita;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class ReceitaServiceImpl implements ReceitaService{
	
	@EJB
	private ConsultasDaoJpa<Receita> reposiroty;

	@Override
	public List<Receita> pesquisar(FiltroReceitaDTO filtro, String... initializeHibernateParameters) {
		return reposiroty.filtrarPesquisa(filtro, Receita.class, initializeHibernateParameters);
	}
}