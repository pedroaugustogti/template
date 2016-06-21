package br.com.localone.admin.gastos.balanco;

import br.com.template.entidades.Usuario;
import br.com.template.util.DinheiroUtil;

public class BalancoGastoBean {

	private Usuario socio;
	
	private Integer quota;
	
	private Double valorBens;
	
	private Double valorReceber;
	
	private Double valorPagar;
	
	public BalancoGastoBean(){
		
		valorPagar = 0.0;
		valorReceber = 0.0;
	}
	
	public String getValorPagarFormat() {
		return DinheiroUtil.doubleEmRealSemSimbolo(valorPagar);
	}

	public String getValorReceberFormat() {
		return DinheiroUtil.doubleEmRealSemSimbolo(valorReceber);
	}

	public String getSaldo() {
		return DinheiroUtil.doubleEmRealSemSimbolo(valorReceber - valorPagar);
	}

	public Usuario getSocio() {
		return socio;
	}

	public void setSocio(Usuario socio) {
		this.socio = socio;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Double getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(Double valorReceber) {
		this.valorReceber = valorReceber;
	}

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Double getValorBens() {
		return valorBens;
	}

	public void setValorBens(Double valorBens) {
		this.valorBens = valorBens;
	}
}
