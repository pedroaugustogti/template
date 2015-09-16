package br.com.localone.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.dao.DespesaDAO;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaServiceImpl implements DespesaService{
	
	@EJB
	private ConsultasDaoJpa<Despesa> reposiroty;
	
	@EJB
	private DespesaDAO despesaDAO;

	@Override
	public List<Despesa> pesquisar(FiltroDespesaDTO filtro, String... hibernateInitialize) {
		return reposiroty.filtrarPesquisa(filtro, Despesa.class, hibernateInitialize);
	}

	@Override
	public Double pesquisaTotalDespesasPeloPeriodo(Date dataInicio, Date dataFinal) {
		return (Double) despesaDAO.pesquisaTotalDespesasPeloPeriodo(dataInicio, dataFinal);
	}
}