package br.com.localone.negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.localone.service.MesaService;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroMesaDTO;
import br.com.template.entidades.Mesa;
import br.com.template.excecao.NegocioException;

@Stateless
public class MesaRegraNegocioImpl implements MesaRegraNegocio{
	
	@EJB
	private MesaService service;

	@Override
	public void proibeCadastroComMesmoNumero(Mesa mesa) throws NegocioException {
		
		List<Mesa> list = mesasPorNumero(mesa);
		
		if (!list.isEmpty()){
			
			throw new NegocioException(Mensagem.MNG025);
		}
	}

	private List<Mesa> mesasPorNumero(Mesa mesa) {
		
		FiltroMesaDTO filtro = new FiltroMesaDTO();
		
		filtro.setNumMesa(mesa.getNumMesa());
		
		List<Mesa> list = service.pesquisar(filtro);
		
		return list;
	}
}