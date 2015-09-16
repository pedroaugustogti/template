package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroQuotaSocioDTO;
import br.com.template.entidades.QuotaSocio;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class QuotaSocioServiceImpl implements QuotaSocioService{
	
	@EJB
	private ConsultasDaoJpa<QuotaSocio> reposiroty;
	
	@Override
	public List<QuotaSocio> pesquisar(FiltroQuotaSocioDTO filtro, String... hibernateInitialize) {
		return reposiroty.filtrarPesquisa(filtro, QuotaSocio.class, hibernateInitialize);
	}
}