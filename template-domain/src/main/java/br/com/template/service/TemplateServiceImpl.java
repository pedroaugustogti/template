package br.com.template.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.entidades.EntidadeExemplo;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class TemplateServiceImpl implements TemplateService{
	
	@EJB
	private ConsultasDaoJpa<EntidadeExemplo> reposiroty;

	@Override
	public List<EntidadeExemplo> pesquisar(EntidadeExemplo filtro) {
		return reposiroty.filtrarPesquisa(filtro, EntidadeExemplo.class);
	}
}