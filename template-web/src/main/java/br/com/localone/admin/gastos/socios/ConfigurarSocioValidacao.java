package br.com.localone.admin.gastos.socios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.localone.service.UsuarioService;
import br.com.template.domain.Empresa;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.QuotaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class ConfigurarSocioValidacao extends AbstractValidacao{
	
	@EJB
	protected UsuarioService usuarioService;
	
	public void socioNaLista(List<QuotaSocio> listSocios, Usuario socioSelecionado) throws NegocioException{
		
		if (listSocios != null && !listSocios.isEmpty()){
			
			for (QuotaSocio configurarSocio : listSocios){
				
				if (configurarSocio.getSocio().getId().equals(socioSelecionado.getId())){
					
					throw new NegocioException(Mensagem.MNG045);
				}
			}
		}
	}
	
	public void naoExisteUsuarioAdministrador(Empresa empresa) throws NegocioException {
		
		List<Usuario> usuariosAdministradores = usuarioService.usuariosComRoleAdmin(empresa);
		
		if (usuariosAdministradores.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG052);
		}
	}
	
	public void verificaQuotaDisponivelParaEmpresa(ConfigurarSocio configurarSocio, QuotaSocio quotaSocio) throws NegocioException {
		
		int somaQuotas = somaQuotasPorConfiguracao(configurarSocio);
		
		somaQuotas += quotaSocio.getQuota();
		
		if (somaQuotas > ConfigurarSocioSuperController.CEM_PORCENTO){
			
			throw new NegocioException(Mensagem.MNG053);
		}
	}
	
	public int somaQuotasPorConfiguracao(ConfigurarSocio config) {
		
		int somaQuotas = 0;
		
		for (QuotaSocio quotaSocio : config.getListQuotaSocio()){
			
			somaQuotas += quotaSocio.getQuota();
		}
		
		return somaQuotas;
	}

	public void camposObrigatorios(QuotaSocio quotaSocio) throws NegocioException {
		
		if (inteiroNaoInformado(quotaSocio.getQuota())){
			
			throw new NegocioException(Mensagem.MNG055);
		}
	}
}