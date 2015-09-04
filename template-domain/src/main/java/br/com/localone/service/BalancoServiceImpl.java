package br.com.localone.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.dao.BalancoDAO;
import br.com.template.entidades.Balanco;
import br.com.template.util.DataUtil;

@Stateless
public class BalancoServiceImpl implements BalancoService{
	
	@EJB
	private BalancoDAO balancoDAO;

	public List<Balanco> pesquisar(Date dataInicio, Date dataFim) {
		
		DataUtil.setMenorHora(dataInicio);
		DataUtil.setMaiorHora(dataFim);
		
		return balancoDAO.pesquisar(dataInicio, dataFim);
	}
}