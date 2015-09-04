package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroEstoqueDTO;
import br.com.template.entidades.Estoque;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class EstoqueServiceImpl implements EstoqueService{
	
	@EJB
	private ConsultasDaoJpa<Estoque> reposiroty;

	@Override
	public List<Estoque> pesquisar(FiltroEstoqueDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Estoque.class);
	}
}