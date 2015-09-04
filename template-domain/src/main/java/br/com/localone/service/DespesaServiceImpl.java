package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.entidades.Despesa;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class DespesaServiceImpl implements DespesaService{
	
	@EJB
	private ConsultasDaoJpa<Despesa> reposiroty;

	@Override
	public List<Despesa> pesquisar(FiltroDespesaDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Despesa.class);
	}
}