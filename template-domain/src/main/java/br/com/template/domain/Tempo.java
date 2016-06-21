package br.com.template.domain;

import java.math.BigInteger;

public enum Tempo {

	HORA(23),
	MINUTO(59),
	SEGUNDO(59);
	
	private int tempoMaximo;
	
	private Tempo(int tempoMaximo){
		
		this.tempoMaximo = tempoMaximo;
	}

	public int getTempoMaximo() {
		return tempoMaximo;
	}

	public int getTempoMinimo() {
		return BigInteger.ZERO.intValue();
	}
	
	public int getTempoMaximoMilisegundos(){
		
		int tempoMaximo = 0;
		int segundo = 1000;
		int minuto = segundo * 60;
		int hora = minuto * 60;
		
		for (Tempo tempo : values()){
			
			if (tempo.equals(HORA)){
				
				tempoMaximo = hora;
			}else if (tempo.equals(MINUTO)){
				tempoMaximo = minuto;
			}else if (tempo.equals(SEGUNDO)){
				tempoMaximo = segundo;
			}
		}
		
		return tempoMaximo;
	}
}