package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroCardapioDTO;
import br.com.template.entidades.Cardapio;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class CardapioServiceImpl implements CardapioService{
	
	@EJB
	private ConsultasDaoJpa<Cardapio> reposiroty;

	@Override
	public List<Cardapio> pesquisar(FiltroCardapioDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Cardapio.class);
	}
	
	@Override
	public List<Cardapio> pesquisar(FiltroCardapioDTO filtro, String...camposInitialize) {
		return reposiroty.filtrarPesquisa(filtro, Cardapio.class, camposInitialize);
	}

}