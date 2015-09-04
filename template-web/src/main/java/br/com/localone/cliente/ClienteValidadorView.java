package br.com.localone.cliente;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.service.MesaService;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Mesa;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class ClienteValidadorView extends AbstractValidacao{
	
	@EJB
	private MesaService mesaService;
	
	public void validaCliente(List<Mesa> list, String cpfCliente) throws NegocioException {
		
		if (StringUtils.isBlank(cpfCliente)){
			throw new NegocioException(Mensagem.MNG034);
		}
		
		if (list.isEmpty()){
			throw new NegocioException(Mensagem.MNG041);
		}
	}
}
