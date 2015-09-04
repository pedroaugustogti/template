package br.com.localone.balanco;

public class BalancoPainelDia {

	//Chave do mapa no manageBean BalancoPainelControle (mapDiario)
	private String chave;
	
	private String diaDaSemana;
	
	private String diaMes;
	
	private String valorFaturado;
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(String diaMes) {
		this.diaMes = diaMes;
	}

	public String getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(String valorFaturado) {
		this.valorFaturado = valorFaturado;
	}
}