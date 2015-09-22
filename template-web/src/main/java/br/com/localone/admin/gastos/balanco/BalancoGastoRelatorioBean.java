package br.com.localone.admin.gastos.balanco;

public class BalancoGastoRelatorioBean {
		
	private String nome;
	private String quota;
	private String valorReceber;
	private String valorPagar;

	public BalancoGastoRelatorioBean(String nome, String quota, String valorReceber, String valorPagar) {
		this.nome = nome;
		this.quota = quota;
		this.valorReceber = valorReceber;
		this.valorPagar = valorPagar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(String valorReceber) {
		this.valorReceber = valorReceber;
	}

	public String getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(String valorPagar) {
		this.valorPagar = valorPagar;
	}
}