package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroProdutoDTO;
import br.com.template.entidades.Produto;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class ProdutoServiceImpl implements ProdutoService{
	
	@EJB
	private ConsultasDaoJpa<Produto> reposiroty;

	@Override
	public List<Produto> pesquisar(FiltroProdutoDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Produto.class);
	}
}