package br.com.localone.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.dao.DespesaEntradaDAO;
import br.com.template.dto.FiltroDespesaEntradaDTO;
import br.com.template.entidades.DespesaEntrada;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaEntradaServiceImpl implements DespesaEntradaService{
	
	@EJB
	private ConsultasDaoJpa<DespesaEntrada> reposiroty;
	
	@EJB
	private DespesaEntradaDAO dao;

	@Override
	public List<DespesaEntrada> pesquisar(FiltroDespesaEntradaDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, DespesaEntrada.class);
	}

	@Override
	public Number pesquisaTotalDespesasPeloPeriodo(Date dataInicio, Date dataFinal) {
		return dao.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
	}
}