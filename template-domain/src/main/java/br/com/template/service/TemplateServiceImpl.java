package br.com.template.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroEntidadeExemploDTO;
import br.com.template.entidades.Pessoa;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class TemplateServiceImpl implements TemplateService{
	
	@EJB
	private ConsultasDaoJpa<Pessoa> reposiroty;

	@Override
	public List<Pessoa> pesquisar(FiltroEntidadeExemploDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Pessoa.class);
	}
}