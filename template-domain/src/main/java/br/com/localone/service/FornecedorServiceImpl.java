package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroFornecedorDTO;
import br.com.template.entidades.Fornecedor;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class FornecedorServiceImpl implements FornecedorService{
	
	@EJB
	private ConsultasDaoJpa<Fornecedor> reposiroty;

	@Override
	public List<Fornecedor> pesquisar(FiltroFornecedorDTO filtro, String... hibernateInit) {
		return reposiroty.filtrarPesquisa(filtro, Fornecedor.class, hibernateInit);
	}

}