package br.com.localone.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroComandaDTO;
import br.com.template.entidades.Comanda;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class ComandaServiceImpl implements ComandaService{
	
	@EJB
	private ConsultasDaoJpa<Comanda> reposiroty;

	@Override
	public Comanda getComandaMesa(FiltroComandaDTO filtro) {
		return reposiroty.primeiroRegistroPorFiltro(filtro, Comanda.class);
	}
}