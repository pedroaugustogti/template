package br.com.localone.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.dto.FiltroFuncionarioDTO;
import br.com.template.entidades.Funcionario;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class FuncionarioServiceImpl implements FuncionarioService{
	
	@EJB
	private ConsultasDaoJpa<Funcionario> reposiroty;

	@Override
	public List<Funcionario> pesquisar(FiltroFuncionarioDTO filtro) {
		return reposiroty.filtrarPesquisa(filtro, Funcionario.class,"pessoa");
	}

}