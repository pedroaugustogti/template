package br.com.localone.admin.gastos.socios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import br.com.localone.service.ConfigurarSocioService;
import br.com.template.domain.Empresa;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.QuotaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;

public abstract class ConfiguracaoSocioSuperController extends AbstractManageBean{
	
	protected ConfigurarSocio configurarSocio;
	
	@EJB
	protected ConfigurarSocioService configurarSocioService;
	
	protected List<Usuario> sociosPorEmpresa(Empresa empresa){
		
		try {
			
			configurarSocio = configurarSocioService.configuracaoPorEmpresa(empresa);
			
			if (configurarSocio == null || configurarSocio.getListQuotaSocio() == null ||
					configurarSocio.getListQuotaSocio().isEmpty()){
				
				return new ArrayList<>();
			}
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		return montaListaSociosEnvolvidosPelaConfiguracao(configurarSocio.getListQuotaSocio());
	}

	private List<Usuario> montaListaSociosEnvolvidosPelaConfiguracao(List<QuotaSocio> listQuotaSocio) {

		List<Usuario> listSocios = new ArrayList<Usuario>();
		
		for (QuotaSocio quotaSocio : listQuotaSocio){
			
			listSocios.add(quotaSocio.getSocio());
		}
		
		return listSocios;
	}
}