package br.com.localone.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.template.domain.Empresa;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroConfigurarSocioDTO;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.excecao.NegocioException;
import br.com.template.generics.ConsultasDaoJpa;

@Stateless
public class ConfigurarSocioServiceImpl implements ConfigurarSocioService{
	
	@EJB
	private ConsultasDaoJpa<ConfigurarSocio> reposiroty;

	@Override
	public ConfigurarSocio configuracaoPorEmpresa(Empresa empresa) throws NegocioException {
		
		if (empresa == null){
			throw new NegocioException(Mensagem.MNG046);
		}
		
		FiltroConfigurarSocioDTO filtro = new FiltroConfigurarSocioDTO();
		
		filtro.setEmpresa(empresa);
		
		return reposiroty.primeiroRegistroPorFiltro(filtro, ConfigurarSocio.class, "listQuotaSocio");
	}
	
}