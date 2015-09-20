package br.com.localone.admin.gastos.despesa;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;

import br.com.localone.negocio.DespesaRegraNegocio;
import br.com.template.domain.Mensagem;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractValidacao;
import br.com.template.framework.InterceptionViewMenssage;

@Stateless
@Interceptors(InterceptionViewMenssage.class)
public class DespesaValidacao extends AbstractValidacao{
	
	@EJB
	private DespesaRegraNegocio regraNegocio;
	
	public void validacao(Despesa despesa) throws NegocioException {
		
		camposObrigatorios(despesa);
		
		verificaPagamentoTotalDespesa(despesa);
	}
	
	public void socioNaLista(List<DespesaSocio> listSocios, Usuario socioSelecionado) throws NegocioException {
		
		if (listSocios != null && !listSocios.isEmpty()){
			
			for (DespesaSocio despesaSocio : listSocios){
				
				if (despesaSocio.getSocio().getId().equals(socioSelecionado.getId())){
					throw new NegocioException(Mensagem.MNG045);
				}
			}
		}
	}
	
	private void verificaPagamentoTotalDespesa(Despesa despesa) throws NegocioException {
		
		double valorPago = 0.0;
		
		for (DespesaSocio ds : despesa.getListDespesaSocio()){
			
			valorPago += ds.getValor();
		}
		
		if (!despesa.getValor().equals(valorPago)){
			
			throw new NegocioException(Mensagem.MNG062);
		}
	}

	private void camposObrigatorios(Despesa despesa) throws NegocioException {
		
		if (despesa.getEmpresa() == null){
			throw new NegocioException(Mensagem.MNG008);
		}
		
		if (StringUtils.isBlank(despesa.getDescricao())){
			throw new NegocioException(Mensagem.MNG009);
		}
		
		if (decimalNaoInformado(despesa.getValor())){
			throw new NegocioException(Mensagem.MNG060);
		}
		
		if (despesa.getListDespesaSocio() == null || despesa.getListDespesaSocio().isEmpty()){
			throw new NegocioException(Mensagem.MNG061);
		}
	}

	public void camposObrigatoriosSociosPagantes(DespesaSocio despesaSocio) throws NegocioException {
		
		if (despesaSocio.getSocio() == null || despesaSocio.getSocio().getId() == null){
			throw new NegocioException(Mensagem.MNG063);
		}
		
		if (decimalNaoInformado(despesaSocio.getValor())){
			throw new NegocioException(Mensagem.MNG064);
		}
	}
}