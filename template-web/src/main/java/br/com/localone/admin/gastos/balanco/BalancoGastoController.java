package br.com.localone.admin.gastos.balanco;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localone.autorizacao.Pagina;
import br.com.localone.service.ConfigurarSocioService;
import br.com.localone.service.DespesaService;
import br.com.localone.service.ReceitaService;
import br.com.template.domain.Empresa;
import br.com.template.domain.Mensagem;
import br.com.template.dto.FiltroBalancoGastoDTO;
import br.com.template.dto.FiltroDespesaDTO;
import br.com.template.dto.FiltroReceitaDTO;
import br.com.template.entidades.Bem;
import br.com.template.entidades.ConfigurarSocio;
import br.com.template.entidades.Despesa;
import br.com.template.entidades.DespesaSocio;
import br.com.template.entidades.QuotaSocio;
import br.com.template.entidades.Receita;
import br.com.template.entidades.ReceitaSocio;
import br.com.template.entidades.Usuario;
import br.com.template.excecao.NegocioException;
import br.com.template.framework.AbstractManageBean;

@ManagedBean(name="painelBalancoGasto")
@ViewScoped
public class BalancoGastoController extends AbstractManageBean{
	
	@EJB
	protected ConfigurarSocioService configurarSocioService;
	
	@EJB
	private ReceitaService receitaService;
	
	@EJB
	private DespesaService despesaService;
	
	private FiltroBalancoGastoDTO filtroBalancoGastoDTO;
	
	private List<BalancoGastoBean> listBalancoGasto;
	
	@PostConstruct
	public void inicio(){
		filtroBalancoGastoDTO = new FiltroBalancoGastoDTO();
		listBalancoGasto = new ArrayList<BalancoGastoBean>();
	}
	
	public void divisaoPorSocio(){
		
		ConfigurarSocio configurarSocio = null;
		
		if (filtroBalancoGastoDTO.getEmpresa() == null){
			
			listBalancoGasto = new ArrayList<BalancoGastoBean>();
			return;
		}
		
		if (naoExisteUsuarioAdministrador(filtroBalancoGastoDTO.getEmpresa())){
			
			enviaMensagem(Mensagem.MNG052);
			inicio();
			return;
		}
		
		try {
			configurarSocio = configurarSocioService.configuracaoPorEmpresa(filtroBalancoGastoDTO.getEmpresa());
			
			if (configurarSocio == null){
				listBalancoGasto = new ArrayList<BalancoGastoBean>();
				return;
			}
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		divisaoPorReceita(configurarSocio);
		divisaoPorDespesa(configurarSocio);
	}
	
	private boolean naoExisteUsuarioAdministrador(Empresa empresa) {
		
		List<Usuario> usuariosAdministradores = usuarioService.usuariosComRoleAdmin(empresa);
		
		return usuariosAdministradores.isEmpty();
	}

	private void divisaoPorDespesa(ConfigurarSocio configurarSocio) {
		
		FiltroDespesaDTO filtroDespesaDTO = new FiltroDespesaDTO();
		filtroDespesaDTO.setEmpresa(filtroBalancoGastoDTO.getEmpresa());
		
		List<Despesa> listDespesa = despesaService.pesquisar(filtroDespesaDTO, "listDespesaSocio");
		
		for (Despesa despesa : listDespesa){
		
			Double valorTotalDespesa = despesa.getValor();
			Double valorDividirEntreSociosConfigurados = valorTotalDespesa;
			
			for (DespesaSocio despesaSocio : despesa.getListDespesaSocio()){
			
				int quota = quotaSocio(despesaSocio.getSocio(), configurarSocio);
				Double valorQuotaSocio = calcularValorPelaQuota(valorTotalDespesa, quota);
				
				boolean usuarioNoBalanco = false;
				
				valorDividirEntreSociosConfigurados = valorDividirEntreSociosConfigurados - valorQuotaSocio;
				
				for (BalancoGastoBean balancoBean : listBalancoGasto){
					
					if (balancoBean.getSocio().getId().equals(despesaSocio.getSocio().getId())){
					
						usuarioNoBalanco = true;
						 
						gerenciaSaldoDespesaSocio(despesaSocio, valorQuotaSocio, balancoBean);
					}
				}
				
				if (!usuarioNoBalanco){
					
					realizaPrimeiraMovientacaoDespesaSocio(despesaSocio, quota, valorQuotaSocio);
				}
			}
			
			//Configuração dos Socios
			descontaSocioConfigurado(configurarSocio, despesa, valorTotalDespesa);
		}
	}

	private void realizaPrimeiraMovientacaoDespesaSocio(DespesaSocio despesaSocio, int quota, Double valorQuotaSocio) {
		
		BalancoGastoBean balancoGastoBean = new BalancoGastoBean();
		balancoGastoBean.setQuota(quota);
		balancoGastoBean.setSocio(despesaSocio.getSocio());
		
		if(despesaSocio.getValor() <= valorQuotaSocio){
			
			double valorPagar =  valorQuotaSocio - despesaSocio.getValor();
			
			balancoGastoBean.setValorPagar(valorPagar);
		}else{
			
			double valorReceber = despesaSocio.getValor() - valorQuotaSocio;
			balancoGastoBean.setValorReceber(valorReceber);
		}
	
		
		listBalancoGasto.add(balancoGastoBean);
	}

	private void gerenciaSaldoDespesaSocio(DespesaSocio despesaSocio, Double valorQuotaSocio, BalancoGastoBean balancoBean) {
		
		if (despesaSocio.getValor().equals(valorQuotaSocio)){
			
			if (valorQuotaSocio >= balancoBean.getValorReceber()){
				
				double valorPagar =  valorQuotaSocio - balancoBean.getValorReceber();
				
				balancoBean.setValorPagar(balancoBean.getValorPagar() + valorPagar);
				balancoBean.setValorReceber(0.0);
				
			}else{
				
				double valorReceber =  balancoBean.getValorReceber() - valorQuotaSocio;
				
				balancoBean.setValorPagar(0.0);
				balancoBean.setValorReceber(valorReceber);
			}
			
		}else if (despesaSocio.getValor() > valorQuotaSocio){
		
			double valorReceber = despesaSocio.getValor() - valorQuotaSocio;
			
			if (valorReceber >= balancoBean.getValorPagar()){
				
				valorReceber = valorReceber - balancoBean.getValorPagar();
				
				balancoBean.setValorReceber(balancoBean.getValorReceber() + valorReceber);
				balancoBean.setValorPagar(0.0);
			}else{
				
				valorReceber = valorReceber - balancoBean.getValorPagar();
				
				balancoBean.setValorReceber(0.0);
				balancoBean.setValorPagar(balancoBean.getValorPagar() - valorReceber);
			}
			
			
		}else if (despesaSocio.getValor() < valorQuotaSocio) {
		
			double valorPagar = valorQuotaSocio - despesaSocio.getValor();
			
			if (valorPagar >= balancoBean.getValorReceber()){
				
				valorPagar = valorPagar - balancoBean.getValorReceber();
				
				balancoBean.setValorPagar(balancoBean.getValorPagar() + valorPagar);
				balancoBean.setValorReceber(0.0);
			}else{
				
				valorPagar = balancoBean.getValorReceber() - valorPagar;
				
				balancoBean.setValorReceber(valorPagar);
				balancoBean.setValorPagar(0.0);
			}
		}
	}

	private void descontaSocioConfigurado(ConfigurarSocio configurarSocio,Despesa despesa, Double valorTotalDespesa) {
		
		for (QuotaSocio quotaSocio : configurarSocio.getListQuotaSocio()){
			
			if (quotaSocioForaDosPagantes(quotaSocio, despesa.getListDespesaSocio())){
				
				Double valorQuota = calcularValorPelaQuota(valorTotalDespesa, quotaSocio.getQuota());
				boolean usuarioNoBalancoConfig = false;
				
				for (BalancoGastoBean balancoBean : listBalancoGasto){
				
					if (balancoBean.getSocio().getId().equals(quotaSocio.getSocio().getId())){
					
						usuarioNoBalancoConfig = true;
						
						if (valorQuota > balancoBean.getValorReceber()){
							
							double valorPagar = valorQuota - balancoBean.getValorReceber();
							balancoBean.setValorPagar(balancoBean.getValorPagar() + valorPagar);
							balancoBean.setValorReceber(0.0);
						}else{
							
							double valorReceber = balancoBean.getValorReceber() - valorQuota;
							balancoBean.setValorReceber(valorReceber);
							balancoBean.setValorPagar(0.0);
						}
					}
				}
				
				if (!usuarioNoBalancoConfig){
				
					BalancoGastoBean balancoGastoSocioConfig = new BalancoGastoBean();
					
					balancoGastoSocioConfig.setQuota(quotaSocio.getQuota());
					balancoGastoSocioConfig.setSocio(quotaSocio.getSocio());
					balancoGastoSocioConfig.setValorPagar(valorQuota);
					
					listBalancoGasto.add(balancoGastoSocioConfig);
				}
			}
		}
	}

	private boolean quotaSocioForaDosPagantes(QuotaSocio quotaSocio,List<DespesaSocio> listDespesaSocio) {
		
		boolean flag = true;
		
		for (DespesaSocio despesaSocio : listDespesaSocio){
			
			if (despesaSocio.getSocio().getId().equals(quotaSocio.getSocio().getId())){
				flag = false;
				break;
			}
		}
		
		return flag;
	}

	private void divisaoPorReceita(ConfigurarSocio configurarSocio) {
		
		FiltroReceitaDTO filtroReceitaDTO = new FiltroReceitaDTO();
		List<Receita> listReceita;
		
		filtroReceitaDTO.setEmpresa(filtroBalancoGastoDTO.getEmpresa());
		
		listReceita = receitaService.pesquisar(filtroReceitaDTO, "listSocio", "listBem");
		
		for (Receita receita : listReceita){
			
			for (Bem bem : receita.getListBem()){
				
				if (bem.getValorVendido() != null){
					
					if (receita.getValorEmDinheiro() == null){
						
						receita.setValorEmDinheiro(bem.getValorVendido());
					}else{
						receita.setValorEmDinheiro(receita.getValorEmDinheiro() + bem.getValorVendido());
					}
				}
			}
			
			for (ReceitaSocio receitaSocio : receita.getListSocio()){
				
				Usuario socioBeneficiado =  receitaSocio.getSocio();
				int quotaSocio = quotaSocio(socioBeneficiado, configurarSocio);
				boolean usuarioNoBalanco = false;
				
				for (BalancoGastoBean balancoBean : listBalancoGasto){
					
					if (balancoBean.getSocio().getId().equals(socioBeneficiado.getId())){
						
						usuarioNoBalanco = true;
						
						Double receitaQuota = calcularValorPelaQuota(receita.getValorEmDinheiro(), quotaSocio);
						
						if (receitaQuota > balancoBean.getValorPagar()){
							
							receitaQuota -= balancoBean.getValorPagar();
							balancoBean.setValorReceber(balancoBean.getValorReceber() + receitaQuota);
							balancoBean.setValorPagar(0.0);
						}else{
							
							balancoBean.setValorPagar(balancoBean.getValorPagar() - receitaQuota);
							balancoBean.setValorReceber(0.0);
						}
					}
				}
				
				if (!usuarioNoBalanco){
					
					BalancoGastoBean balancoGastoBean = new BalancoGastoBean();
					
					balancoGastoBean.setQuota(quotaSocio);
					balancoGastoBean.setSocio(socioBeneficiado);
					balancoGastoBean.setValorReceber(calcularValorPelaQuota(receita.getValorEmDinheiro(), quotaSocio));
					
					listBalancoGasto.add(balancoGastoBean);
				}
			}
		}
	}

	private Double calcularValorPelaQuota(Double valorEmDinheiro, int quotaSocio) {
	
		final int QUOTA_100_PORCENTO = 100;
		
		if (valorEmDinheiro == null || quotaSocio == QUOTA_100_PORCENTO){
			return valorEmDinheiro;
		}
		
		Double porcentagemDecimal = Double.valueOf("0."+quotaSocio);
		
		return valorEmDinheiro * porcentagemDecimal;
	}
	
	private int quotaSocio(Usuario socioBeneficiado, ConfigurarSocio configurarSocio) {

		for (QuotaSocio quotaSocio : configurarSocio.getListQuotaSocio()){
			
			if (quotaSocio.getSocio().getId().equals(socioBeneficiado.getId())){
				return quotaSocio.getQuota();
			}
		}
		
		return 0;
	}

	@Override
	protected Pagina getPaginaManageBean() {
		return Pagina.BALANCO_GASTO;
	}

	public FiltroBalancoGastoDTO getFiltroBalancoGastoDTO() {
		return filtroBalancoGastoDTO;
	}

	public List<BalancoGastoBean> getListBalancoGasto() {
		return listBalancoGasto;
	}
}